function connectMC(ip)
    print("start connectMC, ip:"..ip)
    sk=net.createConnection(net.TCP, 0)
    sk:on("receive", function(sck, c)
        print("[mc]"..c)
        parseMC(c)
        --if parseMC(c) then
        --    sck:send("done\n")
        --else
        --    sck:send("error\n")
        --end
    end)
    sk:on("connection",function(s)
        tmr.stop(1)
        print("connectedMC!!")
        sk:send("id ".._ID.."\n")
    end)
    print("lets do it!")
    sk:connect(5566,ip)
    print("?????")
    tmr.alarm(1,5000,1,function()
        print("try reconnectMC...")
        connectMC(mcip)
    end);
    --sk:send("id ".._ID.."\n")
end

function parseMC(c)
    if c == "on\n" then
        print("turn ON the light!")
        gpio.write(lightPin,gpio.LOW)
        return true
    elseif c == "off\n" then
        print("turn OFF the light!")
        gpio.write(lightPin,gpio.HIGH)
        return true
    else
        print("ERROR, got: "..c)
        return false
    end
end
