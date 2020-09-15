package dev.fls.aideReseau;

import java.net.InetAddress;
import javax.swing.JOptionPane;

/**
 *
 * Cette classe gère tout ce qui est en lien avec le réseau
 * 
 * @author b.siniak
 * @version 1.0
 */
class ReseauManager {
    
    /*
    * Cette fonction sert à déterminer la classe d'une IP
    *
    * @param adresseIp - L'Adresse IP pour laquelle il faut déterminer la classe
    * @param sousReseau - Le sous-réseau de l'adresse 
    *
    */
    public void getIpClass(String adresseIp, String sousReseau) {
        String[] ip = adresseIp.split("\\.");
        String[] mask = sousReseau.split("\\.");
        if (ip.length != 4 || adresseIp.length() > 16) {
            JOptionPane.showMessageDialog(null, "Ceci n'est pas une IP");
            return;
        }
        
        Integer num = Integer.valueOf(ip[0]);
        Integer mask0 = Integer.valueOf(mask[0]);
        if (num <= 127 && mask0 <= 255) {
            JOptionPane.showMessageDialog(null, "Classe A" + sousReseau);
        } else if (num <= 172) {
            JOptionPane.showMessageDialog(null, "Classe B");
        } else if (num <= 192) {
            JOptionPane.showMessageDialog(null, "Classe C");
        } else {
            JOptionPane.showMessageDialog(null, "Classe D");
        }
        
        JOptionPane.showMessageDialog(null, "IP: " + adresseIp);
        JOptionPane.showMessageDialog(null, "Masque: " + sousReseau);
    }

    /*
    * Cette fonction sert à envoyer une requête Ping vers la cible
    * et déterminer si les 2 machines peuvent communiquer
    *
    * @param IP - L'Adresse IP à laquelle envoyer la requête
    *
    */
    public void sendPing(String IP) {
        try {
            InetAddress address = InetAddress.getByName(IP);
            if(address.isReachable(2000)) {
                JOptionPane.showMessageDialog(null, "Pingable");
            } else {
                JOptionPane.showMessageDialog(null, "Time out");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * Cette fonction sert à déterminer la classe d'une IP
    *
    * @param IP - L'Adresse disponible la plus basse
    * @param IP2 - L'Adresse disponible la plus haute
    * @return ipList - La liste des IP disponibles
    *
    */
    public void getDisponibleIp(String IP, String IP2, String masque1, String masque2) {
        String[] bytesIP = IP.split(".", -2);
        String[] bytesIP2 = IP2.split(".", -2);
        int byte1IP1 = Integer.valueOf(bytesIP[0]);
        int byte1IP2 = Integer.valueOf(bytesIP2[0]);
        
        
        IP = byte1IP1 + "0.0.0 à ";
        IP2 = byte1IP2 + ".255.255.255";
        if (byte1IP1 <= 127) {
            // Classe A - 16 Millions d'IP disponibles
            JOptionPane.showMessageDialog(null, "Adresses IP disponibles: 16777214");
            JOptionPane.showMessageDialog(null, "Adresses IP disponibles de " + IP + " à " + IP2);
        } else if (byte1IP1 <= 172) {
            // Classe B - 65534 d'IP disponibles
            JOptionPane.showMessageDialog(null, "Adresses IP disponibles: 65534");
        } else {
            // Classe C - 254 d'IP disponibles
            JOptionPane.showMessageDialog(null, "Adresses IP disponibles: 254");
        }
        String adressesDispo = IP + IP2;
        JOptionPane.showMessageDialog(null, adressesDispo);
    }
}
