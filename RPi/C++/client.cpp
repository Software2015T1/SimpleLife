#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <thread>

using namespace std;
int sockfd;
void listener()
{
    char buffer[128];
    while(recv(sockfd,buffer,sizeof(buffer),0)){
        
        printf("from sever %s\n",buffer);
        bzero(buffer,128);
    }
}
void sender(char *cmd){
    send(sockfd,cmd,sizeof(cmd),0);

}
int main(){
    //int sockfd;
    struct sockaddr_in dest;
    char buffer[128];
    char resp[128]="/MCConnet MC01\0\n";
    sockfd = socket (PF_INET,SOCK_STREAM,0);

    bzero(&dest,sizeof(dest));
    dest.sin_family = PF_INET;
    dest.sin_port = htons(5028);
    dest.sin_addr.s_addr = inet_addr("192.168.1.183");

    printf("error\n");
    if(connect(sockfd,(struct sockaddr*)&dest,sizeof(dest))==-1){
        printf("error\n");
    }
    //thread mThread( listener );
    if(send(sockfd,resp,sizeof(resp),0))
        printf("send_finished\n");
    //bzero(buffer,128);
    //recv(sockfd,buffer,sizeof(buffer),0);

    //printf("from sever %s\n",buffer);
    

    close(sockfd);

    return 0;


}