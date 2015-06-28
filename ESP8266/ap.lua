function checkWifiInfoFile()
    if file.open("wifiinfo.txt", "r") then
        print("found a wifiinfo.txt file!")
        ssid = file.readline()
        pwd = file.readline()
        mcip = file.readline()
        file.close()
        wifi.setmode(wifi.STATION)
        connectWifi(ssid,pwd)
        tmr.delay(3000000)
        if(wifi.sta.getip()==nil) then
            --file.remove("wifiinfo.txt")
            apMode()
            return
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
    print("now starting AP")
    wifi.setmode(wifi.SOFTAP)
    apcfg={}
    apcfg.ssid=_ID
    wifi.ap.config(apcfg)
    apcfg = nil

    server = net.createServer(net.TCP, 30)
    server:listen(2266, function(c)
        c:on("receive", function(cl, data) 
            print("received data from client!!")
            print(data)
            r = parseWifiInfo(data)
            if r == nil then
                cl:send("failed: data format wrong")
            else
                wifi.setmode(wifi.STATIONAP)
                connectWifi(r.ssid, r.pwd, r.mcip)
                print("data parsed succeded!")
                print("ssid = "..r.ssid)
                print("pwd = "..r.pwd)
                print("mcip = "..r.mcip)
                tmr.delay(2000000)
                if(wifi.sta.getip() == nil) then
                    print("nil@@ send wifi fail")
                    cl:send("connect wifi failed")
                else
                    print("success wifi!")
                    cl:send("successfully connect to wifi!!")
                    saveWifiToFile(r)
                    closeServer()
                    connectMC(r.mcip);
                    r = nil
                end
            end
        end)
    end)
end
function closeServer()
    server:close()
    server = nil
    wifi.setmode(wifi.STATION)
    wifi.sta.connect()
end
function parseWifiInfo(data)
    local t={};i=1;ret={}
    for w in string.gmatch(data, '([^,]+)') do
        t[i] = w
        i=i+1
    end
    ret.ssid=t[1]--haha
    ret.pwd=t[2]--haha
    ret.mcip=t[3]--haha
    return ret
end
function saveWifiToFile(t)
    file.remove("wifiinfo.txt")
    file.open("wifiinfo.txt", "w+")
    file.writeline(t.ssid)
    file.writeline(t.pwd)
    file.writeline(t.mcip)
    file.close()
end
-------------------------------------------------
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