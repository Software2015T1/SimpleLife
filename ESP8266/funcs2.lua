function connectWifi(ssid, pwd)
    wifi.sta.config(ssid,pwd)
    wifi.sta.connect()
    if(wifi.sta.getip()) then
        print("wifi connected, ip = "..wifi.sta.getip())
    end
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
