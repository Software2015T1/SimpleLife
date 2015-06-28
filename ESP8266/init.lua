print("dofile")
dofile("funcs.lua")
dofile("funcs2.lua")

_ID = "SimpleLifeLight:LJ0S65SD45"
print("start runing: ".._ID)
lightPin = 4
gpio.mode(lightPin,gpio.OUTPUT)

wifi.setmode(wifi.STATION)
wifi.sta.connect()

wifiIp = wifi.sta.getip()

if wifiIp == nil then
    print('not connected to wifi')
    checkWifiInfoFile()
else
    print('connected to wifi, ip='..wifiIp)
    wifiIp = nil
    file.open("wifiinfo.txt","r")
    file.readline()
    file.readline()
    ip = file.readline()
    connectMC(ip)
    file.close()
    ip=nil
end
