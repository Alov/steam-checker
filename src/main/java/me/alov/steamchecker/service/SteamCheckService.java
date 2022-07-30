package me.alov.steamchecker.service;

import lombok.extern.slf4j.Slf4j;
import me.alov.steamchecker.model.Info;
import me.alov.steamchecker.model.SteamApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SteamCheckService {

    private static String CHECK_URL = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/";
    private static String API_KEY = "54076FFC07F4E74FEB6215B533140F4E";

    @Autowired
    private RestTemplate steamSender;


    public List<Info> massCheck(String steamIds) {
        List<Info> infos = new ArrayList<>();

        String[] ids = steamIds.split(",");
        for (int i = 0; i < ids.length; i++) {
            Info info = this.checkSingleClient(ids[i]);
            infos.add(info);
        }

        return infos;
    }

    public Info checkSingleClient(String steamId) {
        String fullUrl = CHECK_URL + "?key=" + API_KEY + "&" + "steamids=" + steamId;
        SteamApiResponse steamApiResponse = steamSender.exchange(fullUrl, HttpMethod.GET, null, SteamApiResponse.class).getBody();

        return steamApiResponse.getResponse().getPlayers().get(0);
    }


}
