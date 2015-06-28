ssid = "YuHuai "
pwd = "b00504014"
mcip = "192.168.43.49"
wifi.setmode(wifi.STATION)
wifi.sta.config(ssid, pwd)
ssid, pwd = nil, nil

connectMC(mcip)
mcip = nil