ssid = "Green"
pwd = "green82028"
mcip = "192.168.1.14"
wifi.setmode(wifi.STATION)
wifi.sta.config(ssid, pwd)
ssid, pwd = nil, nil
connectMC(mcip)