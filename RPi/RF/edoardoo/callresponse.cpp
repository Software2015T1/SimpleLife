#include <string>
#include <getopt.h>
#include <cstdlib>
#include <sstream>
#include <iostream>
#include <RF24/RF24.h>
#include "time.h"

using namespace std;
//RF24 radio("/dev/spidev0.0",8000000 , 25);  
//RF24 radio(RPI_V2_GPIO_P1_15, RPI_V2_GPIO_P1_24, BCM2835_SPI_SPEED_8MHZ);

RF24 radio(RPI_V2_GPIO_P1_22, RPI_V2_GPIO_P1_24, BCM2835_SPI_SPEED_8MHZ);
//const int role_pin = 7;
//const uint64_t pipes[2] = { 0xF0F0F0F0E1LL, 0xF0F0F0F0D2LL };
const uint8_t pipes[][6] = {"1Node","2Node"};

// hack to avoid SEG FAULT, issue #46 on RF24 github https://github.com/TMRh20/RF24.git
unsigned long  got_message;

typedef enum { role_ping_out = 1, role_pong_back } role_e;
const char* role_friendly_name[] = { "invalid", "Ping out", "Pong back"};
role_e role = role_pong_back;

uint8_t counter = 1;

void setup(void){
   //Prepare the radio module
   printf("\nGettingStarted_CallResponse");
   printf("\nPreparing interface\n");
   radio.begin();
   radio.setAutoAck(1);
   radio.enableAckPayload();
   radio.setRetries( 0, 15);
   radio.setPayloadSize(1);
   //	radio.setChannel(0x4c);
   //	radio.setPALevel(RF24_PA_MAX);
   //	radio.setPALevel(RF24_PA_MAX);

   radio.openWritingPipe(pipes[1]);
   radio.openReadingPipe(1,pipes[0]);
   radio.startListening();
   radio.powerUp();
   radio.printDetails();

}

void loop(void) {

   /*********** Ping Out Role ************/

   if (role == role_ping_out){

      radio.stopListening();
      printf("Now sending %d as payload. ",counter);
      //unsigned long time = micros();
      clock_t start = clock();

      uint8_t pipeNo, gotByte;
      if ( radio.write((void*) &counter, sizeof(counter)) ){
         cout << radio.isAckPayloadAvailable();
         if(!radio.available(&pipeNo)){
            //printf("Got blank response. round-trip delay: %lu microseconds\n\r",micros()-time);     
            printf("Got blank response. round-trip delay: %lu ms\n\r", (clock()-start)*1000/CLOCKS_PER_SEC);     
         }else{ 
            while(radio.available(&pipeNo) ){
               radio.read( &gotByte, sizeof(gotByte) );
               //printf("Got response %d, round-trip delay: %lu microseconds\n\r",gotByte,micros()-time);
               printf("Got response %i, round-trip delay: %lu ms\n\r",gotByte, (clock()-start)*1000/CLOCKS_PER_SEC);
               counter++;
            }
         }

      }else{
         printf("Sending failed.\n\r");
      }

      delay(1000);  // Try again later
   }


   /******** Pong Back Role **********/

   if ( role == role_pong_back ) {
      uint8_t pipeNo, gotByte;
      while( radio.available(&pipeNo)){
         radio.read( &gotByte, sizeof(gotByte) );
         radio.writeAckPayload(pipeNo,&gotByte, 1 );
         printf("Sent response %d \n\r", gotByte);  
      }
   }
}

int main( int argc, char ** argv){

   setup();
   string role_in;

   cout << "pick a role: 'ping' or 'pong'" << endl;
   cin >> role_in;

   if(role_in == "ping") {
      role = role_ping_out;
      radio.openWritingPipe(pipes[0]);
      radio.openReadingPipe(1,pipes[1]);
   }
   else if(role_in == "pong") {
      role = role_pong_back;
      radio.openWritingPipe(pipes[1]);
      radio.openReadingPipe(1,pipes[0]);
      radio.startListening();
   }
   else {
      cout << "invalid option" << endl;
      return -1;
   }

   printf("\n Talking with my NRF24l01+ friends out there....\n");

   while(true){

      loop();

   }


   return 0;
}
