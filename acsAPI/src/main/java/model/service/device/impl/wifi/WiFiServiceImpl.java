/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.device.impl.wifi;

import br.net.gvt.efika.acs.model.device.enums.DeviceTR;
import br.net.gvt.efika.acs.model.device.wifi.WifiNets;
import br.net.gvt.efika.acs.model.exception.TratativaExcessao;
import com.alcatel.hdm.service.nbi2.NbiDeviceData;
import java.util.ArrayList;
import java.util.List;
import model.service.device.GenericDeviceService;
import model.service.device.impl.SetParameters;
import motive.hdm.synchdeviceops.ParameterValueStructDTO;

/**
 *
 * @author G0042204
 */
public class WiFiServiceImpl extends GenericDeviceService implements WiFiService {

    /**
     * Consulta configuração WiFi e ativa caso inativa.
     *
     * @param device
     * @return
     * @throws Exception
     */
    @Override
    public WifiNets consultar(NbiDeviceData device) throws Exception {

//        List<String> paths = new ArrayList<>();
//        try {
//            paths.add("InternetGatewayDevice.LANDevice.1.WLANConfiguration.");
//            GetParameterValuesResponseDTO wifis = synch().getParametersValues(device, paths);
//            return new WifiNets(WifiParser.parse(wifis));
//        } catch (Exception e) {
//            paths.add("Device.WiFi.");
//            GetParameterValuesResponseDTO wifis = synch().getParametersValues(device, paths);
//            return new WifiNets(WifiParser.parse(wifis));
//        }

        try {
            return new WifiNets(synch().getWifiInfoFull(device));
        } catch (Exception e) {
            TratativaExcessao.treatException(e);
        }
        return null;
    }

    @Override
    public void ativar(NbiDeviceData device) throws Exception {
        System.out.println("Ativar WiFi...");

        try {
            List<ParameterValueStructDTO> lst = new ArrayList<>();
            lst.add(SetParameters.ativarBroadcastWifi(DeviceTR.TR_098, 1));
            lst.add(SetParameters.ativarStatusWifi(DeviceTR.TR_098, 1));
            lst.add(SetParameters.ativarWifi(DeviceTR.TR_098, 1));
            synch().setParametersValues(device, lst);
//            try {
//                List<ParameterValueStructDTO> l = new ArrayList<>();
//                l.add(SetParameters.ativarBroadcastWifi(DeviceTR.TR_098, 5));
//                l.add(SetParameters.ativarStatusWifi(DeviceTR.TR_098, 5));
//                l.add(SetParameters.ativarWifi(DeviceTR.TR_098, 5));
//                synch().setParametersValues(device, l);
//            } catch (Exception e) {
//            }
        } catch (Exception e) {
            List<ParameterValueStructDTO> lst = new ArrayList<>();
            lst.add(SetParameters.ativarBroadcastWifi(DeviceTR.TR_181, 1));
            lst.add(SetParameters.ativarStatusWifi(DeviceTR.TR_181, 1));
            lst.add(SetParameters.ativarWifi(DeviceTR.TR_181, 1));
            try {
                synch().setParametersValues(device, lst);
            } catch (Exception ex) {
                TratativaExcessao.treatException(ex);
            }

//            try {
//                List<ParameterValueStructDTO> l = new ArrayList<>();
//                l.add(SetParameters.ativarBroadcastWifi(DeviceTR.TR_181, 5));
//                l.add(SetParameters.ativarStatusWifi(DeviceTR.TR_181, 5));
//                l.add(SetParameters.ativarWifi(DeviceTR.TR_181, 5));
//                synch().setParametersValues(device, l);
//            } catch (Exception ex) {
//            }
        }
    }

    @Override
    public void desativar(NbiDeviceData device) throws Exception {
        System.out.println("Desativar WiFi...");

        List<ParameterValueStructDTO> lst = new ArrayList<>();
//        lst.add(SetParameters.DESATIVAR_WIFI);
        try {
            synch().setParametersValues(device, lst);
        } catch (Exception ex) {
            TratativaExcessao.treatException(ex);
        }
    }

    @Override
    public WifiNets alterar(NbiDeviceData device, WifiNets wifis) throws Exception {
        try {
//            List<ParameterValueStructDTO> lst = WifiParser.parse(wifis.getWifi().get(0), DeviceTR.TR_098);
//            synch().setParametersValues(device, lst);
            if (wifis.getWifi().size() > 1) {
                System.out.println("REDE 1");
                synch().setWifiInfoFull(device, wifis.getWifi().get(0));
                System.out.println("_________________________________________________");
                Thread.sleep(8000);
                System.out.println("REDE 5");
                Integer rede5Pos = device.getModelName().equalsIgnoreCase("RTF3505VW-N2") ? 4 : wifis.getWifi().size() - 1;
                synch().setWifiInfoFull(device, wifis.getWifi().get(rede5Pos));
                System.out.println("_________________________________________________5");
            }else{
//                String wichone = wifis.getWifi().get(0).getIndex().equalsIgnoreCase("1") ? wifis.getWifi().get(0).getIndex() : "5";
                synch().setWifiInfoFull(device, wifis.getWifi().get(0));
            }

        } catch (Exception e) {
            e.printStackTrace();
//            List<ParameterValueStructDTO> lst = WifiParser.parse(wifis.getWifi().get(0), DeviceTR.TR_181);
//            synch().setParametersValues(device, lst);
        }


        return consultar(device);
    }

}
