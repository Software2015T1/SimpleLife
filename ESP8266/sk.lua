sk=net.createConnection(net.TCP, 0)
    sk:on("receive", function(sck, c) print(c) end )
    sk:connect(5566,"192.168.1.188")
    sk:send("GET \n")