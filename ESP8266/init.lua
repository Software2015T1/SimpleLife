_ID = "SimpleLifeLight:LJ0S65SD45"
print("start runing: ".._ID)
lightPin = 4
gpio.mode(lightPin,gpio.OUTPUT)
--------------------------------
print("loading socket.lua...")
--must
dofile("socket.lua")
--choose one
--dofile("ap.lua")
print("loading hardcode.lua...")
dofile("hardcode.lua")
collectgarbage()
