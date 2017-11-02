/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selahaddin.service;

import com.selahaddin.database.DBCRUD;
import com.selahaddin.model.Metro;
import com.selahaddin.model.agancy18;
import com.selahaddin.model.fareAttribute;
import com.selahaddin.model.fareTransfer;
import com.selahaddin.model.routeStart;
import com.selahaddin.model.tranferDuration;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.xml.sax.SAXException;
import javax.xml.xpath.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author lenova
 */
public class xmlParser {

    static List<fareAttribute> listfareAtt = new ArrayList<fareAttribute>();
    static List<fareTransfer> listfareTransfer = new ArrayList<fareTransfer>();
    static List<tranferDuration> listTransferDuration = new ArrayList<tranferDuration>();
    long firstStartTime = 0;
    static double fare = 0.0;
    int transfer_count = 1;

    public JSONObject RouteStartFare(routeStart s1) {
        JSONObject js1 = new JSONObject();

        int index = containsListFareAttribute(s1.getRoute_id());
        if (index >= 0) {
            fare += listfareAtt.get(index).getPrice();
            js1.put("fare", listfareAtt.get(index).getPrice());
            js1.put("routeId", s1.getRoute_id());
            js1.put("agencyId", s1.getAgency_id());
        } else {
            int trasfer_duration = getTransferDuration(s1.getAgency_id()) * 1000;
            if (firstStartTime + trasfer_duration < s1.getStartTime()) {
                firstStartTime = s1.getStartTime();
                transfer_count = 1;
            }
            fare += getPrice(s1.getAgency_id(), transfer_count);
            js1.put("fare", getPrice(s1.getAgency_id(), transfer_count));
            js1.put("routeId", s1.getRoute_id());
            js1.put("agencyId", s1.getAgency_id());
            if (transfer_count < 7) {
                transfer_count++;
            } else {
                transfer_count = 1;
            }

        }
        return js1;
    }

    public int getTransferDuration(int agency_id) {
        for (int i = 0; i < listTransferDuration.size(); i++) {
            if (listTransferDuration.get(i).getAgency_id() == agency_id) {
                return listTransferDuration.get(i).getTranfer_duration();
            }
        }
        return -1;
    }

    public int containsListFareAttribute(int route_id) {
        for (int i = 0; i < listfareAtt.size(); i++) {
            if (listfareAtt.get(i).getRoute_id() == route_id) {
                return i;
            }
        }
        return -1;
    }

    public double getPrice(int agency_id, int transfer_id) {
        for (int i = 0; i < listfareTransfer.size(); i++) {
            if (listfareTransfer.get(i).getAgency_id() == agency_id && listfareTransfer.get(i).getTransfer_id() == transfer_id) {
                return listfareTransfer.get(i).getPrice();
            }
        }
        return 0;
    }

    public JSONObject metroFare(Metro m1) {
        JSONObject js1 = new JSONObject();
        List<fareTransfer> listMetro = new ArrayList<fareTransfer>();
        for (int i = 0; i < listfareTransfer.size(); i++) {
            if (listfareTransfer.get(i).getAgency_id() == 11) {
                listMetro.add(listfareTransfer.get(i));

            }
        }
        if (listMetro.size() > 0) {
            Collections.sort(listMetro, new Comparator<fareTransfer>() {
                public int compare(fareTransfer o1, fareTransfer o2) {
                    return o1.getTransfer_id() - o2.getTransfer_id();
                }
            });
            for (int i = 0; i < listMetro.size(); i++) {
                int stop1 = listMetro.get(i).getTransfer_id();
                int stop2;
                if (i < listMetro.size() - 1) {
                    stop2 = listMetro.get(i + 1).getTransfer_id();
                } else {
                    stop2 = 10000;
                }
                if (m1.getStops_count() >= stop1 && m1.getStops_count() < stop2) {
                    fare += listMetro.get(i).getPrice();
                    js1.put("fare", listMetro.get(i).getPrice());
                    js1.put("routeId", m1.getRoute_id());
                    js1.put("agencyId", m1.getAgency_id());
                    break;
                }

            }
        }
        return js1;
    }

    public JSONObject agencyFare18(agancy18 m1) {
        JSONObject js1 = new JSONObject();
        List<fareTransfer> list = new ArrayList<fareTransfer>();
        for (int i = 0; i < listfareTransfer.size(); i++) {
            if (listfareTransfer.get(i).getAgency_id() == 18) {
                list.add(listfareTransfer.get(i));

            }
        }
        if (list.size() > 0) {
            Collections.sort(list, new Comparator<fareTransfer>() {
                public int compare(fareTransfer o1, fareTransfer o2) {
                    return o1.getTransfer_id() - o2.getTransfer_id();
                }
            });
            for (int i = 0; i < list.size(); i++) {
                int stop1 = list.get(i).getTransfer_id() * 1000;
                int stop2;
                if (i < list.size() - 1) {
                    stop2 = list.get(i + 1).getTransfer_id() * 1000;
                } else {
                    stop2 = 1000000;
                }
                if (m1.getDistance() >= stop1 && m1.getDistance() <= stop2) {
                    double routeprice = 0.0;
                    if (i < list.size() - 1) {
                        routeprice = list.get(i).getPrice();
                    } else {
                        int temp = (int) Math.ceil((m1.getDistance() - (list.get(i).getTransfer_id() * 1000)) / 1000);
                        routeprice = list.get(i - 1).getPrice() + (temp * 0.1);
                    }
                    fare += routeprice;
                    js1.put("fare", routeprice);
                    js1.put("routeId", m1.getRoute_id());
                    js1.put("agencyId", m1.getAgency_id());
                    break;
                }

            }
        }
        return js1;
    }

    public String xmlParser(String stringUrl) throws SAXException, IOException, XPathExpressionException, ParseException {
        String output = "";
        DBCRUD dbcrud = new DBCRUD();
        if (listfareAtt.isEmpty()) {
            listfareAtt = dbcrud.getFareAttributes();
        }
        if (listfareTransfer.isEmpty()) {
            listfareTransfer = dbcrud.getFareTransfer();
        }
        if (listTransferDuration.isEmpty()) {
            listTransferDuration = dbcrud.getTransferDuration();
        }
        JSONObject createJSON = new JSONObject();
        JSONArray jsArrLegs = new JSONArray();

        String json = "";

        try {
            URL url = new URL(stringUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream()), "UTF-8"));
            output = br.readLine();
            //  System.out.println(output);
            conn.disconnect();
            JSONObject js = new JSONObject(output);
            JSONObject planObject = js.getJSONObject("plan");
            int item_lengh = planObject.getJSONArray("itineraries").length();
            for (int s = 0; s < item_lengh; s++) {
                firstStartTime=0;
                fare = 0.0;
                transfer_count = 1;
                JSONArray legsItems = planObject.getJSONArray("itineraries").getJSONObject(s).getJSONArray("legs");
                JSONArray ja1 = new JSONArray();
                for (int i = 0; i < legsItems.length(); i++) {
                    JSONObject legsObject = (JSONObject) legsItems.get(i);
                    String mode = legsObject.getString("mode");
                    if (!mode.equals("WALK")) {
                        String obj_agency_id = legsObject.getString("agencyId");
                        if (!obj_agency_id.equals("11") && !obj_agency_id.equals("18")) {
                            if (i == 0) {
                                firstStartTime = legsObject.getLong("startTime");
                            }
                            long startTime = legsObject.getLong("startTime");
                            int route_id = Integer.parseInt(legsObject.getString("routeId").split(":")[1]);
                            routeStart s1 = new routeStart(route_id, startTime, Integer.parseInt(obj_agency_id));
                            ja1.put(RouteStartFare(s1));
                        } else if (obj_agency_id.equals("11")) {
                            String route_id = legsObject.getString("routeId").split(":")[1];
                            JSONObject fromObject = legsObject.getJSONObject("from");
                            int fromStopSequence = fromObject.getInt("stopSequence");
                            JSONObject toObject = legsObject.getJSONObject("to");
                            int toStopSequence = toObject.getInt("stopSequence");
                            Metro m1 = new Metro(route_id, Integer.parseInt(obj_agency_id), toStopSequence - fromStopSequence);
                            ja1.put(metroFare(m1));
                        } else if (obj_agency_id.equals("18")) {
                            int route_id = Integer.parseInt(legsObject.getString("routeId").split(":")[1]);
                            double distance = legsObject.getDouble("distance");
                            agancy18 m1 = new agancy18(route_id, Integer.parseInt(obj_agency_id), distance);
                            ja1.put(agencyFare18(m1));
                        }
                    }
                }
                JSONArray createJSONArray = new JSONArray();
                createJSONArray.put(ja1);
                JSONObject js2 = new JSONObject();
                JSONObject js1 = new JSONObject();
                js2.put("fareAll", fare);
                js2.put("fares", createJSONArray);

                js1.put("leg", js2);
                jsArrLegs.put(js1);

            }

            createJSON.put("legs", jsArrLegs);
            json = createJSON.toString();
        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        if (json.length() < 5) {
            json = "lütfen otp yi calistiriniz!!! ";
        }
        return json;
    }
}
