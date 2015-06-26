<<<<<<< Updated upstream
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

function checkWifiInfoFile()
    if file.open("wifiinfo.txt", "r") then
=======
function checkWifiInfoFile()
    if file.open("wifiinfo.txt", "r") then
        print("found a wifiinfo.txt file!")
>>>>>>> Stashed changes
        ssid = file.readline()
        pwd = file.readline()
        mcip = file.readline()
        file.close()
        wifi.setmode(wifi.STATION)
        connectWifi(ssid,pwd)
        if(wifi.sta.getip()==nil) then
            file.remove("wifiinfo.txt")
<<<<<<< Updated upstream
=======
            apMode()
            return
>>>>>>> Stashed changes
        end
        connectMC(mcip)
        ssid=nil
        pwd=nil
        mcip=nil
    else
        apMode()
    end
end

function apMode()
<<<<<<< Updated upstream
=======
    print("now starting AP")
>>>>>>> Stashed changes
    wifi.setmode(wifi.SOFTAP)
    apcfg={}
    apcfg.ssid=_ID
    wifi.ap.config(apcfg)
    apcfg = nil

    server = net.createServer(net.TCP, 30)
    server:listen(2266, function(c) 
<<<<<<< Updated upstream
=======
        print("connected! client")
>>>>>>> Stashed changes
        c:on("receive", function(c, data) 
            print("received data from client!!")
            print(data)
            r = parseWifiInfo(data)
            if r == nil then
                c:send("failed")
            else
                wifi.setmode(wifi.STATIONAP)
                connectWifi(r.ssid, r.pwd, r.mcip)
                print("data parsed succeded!")
                print("ssid = "..r.ssid)
                print("pwd = "..r.pwd)
                print("mcip = "..r.mcip)
                saveWifiToFile(r)
                closeServer()
                connectMC(r.mcip);
                r = nil
            end
        end)
    end)
end

function closeServer()
    server:close()
    server = nil
    wifi.setmode(wifi.STATION)
end

function connectWifi(ssid, pwd)
    wifi.sta.config(ssid,pwd)
    wifi.sta.connect()
<<<<<<< Updated upstream
    print("wifi connected, ip = "..wifi.sta.getip())
=======
    if(wifi.sta.getip()) then
        print("wifi connected, ip = "..wifi.sta.getip())
    end
>>>>>>> Stashed changes
end

function saveWifiToFile(t)
    file.remove("wifiinfo.txt")
    file.open("wifiinfo.txt", "w+")
    file.writeline(t.ssid)
    file.writeline(t.pwd)
    file.writeline(t.mcip)
    file.close()
end


function connectMC(ip)
    sk=net.createConnection(net.TCP, 0)
    sk:on("receive", function(sck, c)
        print("[mc]"..c)
        if parseMC(c) then
            sck:send("done")
        else
            sck:send("error")
        end
    end )
    sk:connect(5566,ip)
    sk:send("id ".._ID)
end

function parseWifiInfo(data)
    local t={};i=1;ret={}
    for w in string.gmatch(data,"%s") do
        t[i] = w
        i=i+1
    end
    ret.ssid=t[1]--haha
    ret.pwd=t[2]--haha
    ret.mcip=t[3]--haha
    return ret
end

function parseMC(c)
    if c == "on" then
        gpio.write(lightPin,gpio.LOW)
        return true
    elseif c == "off" then
        gpio.write(lightPin,gpio.HIGH)
        return true
    else
        print("ERROR")
        return false
    end
end
<<<<<<< Updated upstream
=======

----------------------------------
--  CODE STARTS HERE  ------------
----------------------------------
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

>>>>>>> Stashed changes
