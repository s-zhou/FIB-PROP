package Dominio;

import JSON.JSONArray;
import JSON.JSONObject;
import JSON.parser.JSONParser;
import JSON.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Horari {

    private Map<Sessio, ArrayList<UAH>> assignacio;

    private Map<String, Matriu> horari;

    public Map<Sessio, ArrayList<UAH>> getAssignacio() {
        return assignacio;
    }

    public void setAssignacio(Map<Sessio, ArrayList<UAH>> assignacio) {
        this.assignacio = assignacio;
    }

    public Horari() {
        assignacio = new HashMap<>();
        horari = new HashMap<>();
    }

    public void assignarUAH(Sessio se, UAH uah) {

        ArrayList<UAH> temp;

        if (assignacio.containsKey(se)) {
            temp = assignacio.get(se);
            temp.add(uah);
            assignacio.put(se, temp);
        }
        else {
            temp = new ArrayList<>();
            temp.add(uah);
            assignacio.put(se,temp);
        }

        int i = uah.getDia().ordinal();
        int j = uah.getHora();

        Casella c = new Casella();

        c.setNomAssig(se.getAssignatura().getNom());
        c.setNumGrup(se.getIdGrup());
        c.setTipus(se.getTipus());

        Matriu m;

        if (horari.containsKey(uah.getAula())) {
            m = horari.get(uah.getAula());
        }
        else {
            m = new Matriu();
        }

        m.assignarCasella(i,j,c);

        horari.put(uah.getAula().getId(),m);
    }


    public void eliminarUAH(Sessio se, UAH uah) {
        if (assignacio.containsKey(se)) {
            if (assignacio.get(se).contains(uah))
                assignacio.get(se).remove(uah);

            if (assignacio.get(se).isEmpty())
                assignacio.remove(se);
        }
    }

    public boolean valida(Sessio s, UAH uah) {
        return RestriccioBinaria.validaSolucio(this, s, uah);
    }

    public boolean esfallo() {
        return this.assignacio.isEmpty();
    }

    public boolean existeixUAH(Sessio s, UAH uah) {
        for (Sessio se : assignacio.keySet())
            for (UAH uah1 : assignacio.get(se))
                if ((uah == uah1) && (s != se)) return true;

        return false;
    }

    public void escriure() {
        for (Sessio s : assignacio.keySet()) {
            System.out.println(s.getAssignatura().getNom() + " " + s.getIdGrup() + " " + s.getTipus());
            System.out.println("----" + assignacio.get(s).size() +  "----");
            for (UAH uah : assignacio.get(s)) {
                System.out.println(uah.getDia());
                System.out.println(uah.getHora());
                System.out.println(uah.getAula().getId() + "\n");
            }

        }
    }
    public boolean assignacioCompelta(Sessio s) {
        if(assignacio.containsKey(s)) {
            int duracio = 0;
            switch (s.getTipus()) {
                case TEORIA:
                    duracio = s.getAssignatura().getnHoresT();
                    break;
                case LABORATORI:
                    duracio = s.getAssignatura().getnHoresL();
                    break;
                case PROBLEMES:
                    duracio = s.getAssignatura().getnHoresP();
                    break;
            }
            if (assignacio.get(s).size() == duracio)
                return true;
        }
        return false;
    }

    public void mapejaHorari() throws Exception {
        inout io = new inout();
        for (Sessio s : assignacio.keySet()) {
            //io.writeln(s.getAssignatura().getNom() + " " + s.getIdGrup() + " " + s.getTipus());
            for (UAH uah : assignacio.get(s)) {
                String nomAula = uah.getAula().getId();
                int i = uah.getDia().ordinal();
                int j = uah.getHora();
                //io.writeln(nomAula + " " + uah.getDia() + " " + j + "\n");
                Casella c = new Casella(s.getAssignatura().getNom(), s.getIdGrup(), s.getTipus());
                if (horari.containsKey(nomAula))
                    horari.get(nomAula).assignarCasella(i, j, c);
                else {
                    Matriu m = new Matriu();
                    m.assignarCasella(i, j, c);
                    horari.put(nomAula, m);
                }
            }
        }
    }

    public Map<String, Matriu> getHorari() {
        return horari;
    }

    public void imprimirHorari() throws Exception {
        inout io = new inout();
        for (String aula : horari.keySet()) {
            io.writeln("");
            io.writeln("");
            io.writeln("---------------");
            io.writeln("Aula " + aula);
            io.writeln("---------------");
            io.writeln("");

            io.write("               ");
            for (Enumeracio.Dia dia : Enumeracio.Dia.values()) {
                io.write(dia.toString());
                for (int i = dia.toString().length(); i < 15; i++) {
                    io.write(" ");
                }
            }
            io.writeln("");
            for (int hora = PlaEstudis.getHoraInici(); hora < PlaEstudis.getHoraFi(); hora++) {
                io.write(hora + "h       ");
                for (Enumeracio.Dia dia : Enumeracio.Dia.values())
                if (horari.get(aula).getCasella(dia.ordinal(), hora) == null)
                    io.write("    --------    ");
                else {
                    String nomAssig = horari.get(aula).getCasella(dia.ordinal(), hora).getNomAssig();
                    int grup = horari.get(aula).getCasella(dia.ordinal(), hora).getNumGrup();
                    String sigla = "?";
                    switch (horari.get(aula).getCasella(dia.ordinal(), hora).getTipus()) {
                        case TEORIA:
                            sigla = "T";
                            break;
                        case LABORATORI:
                            sigla = "L";
                            break;
                        case PROBLEMES:
                            sigla = "P";
                            break;
                    }
                    io.write("    " + nomAssig + " " + grup + " " + sigla + "    ");
                }




                io.writeln("");
            }
        }

    }

    @SuppressWarnings("unchecked")
    public void guardarHorari(String nomfitxer){
        // Array general
        JSONArray aules = new JSONArray();

        for (String nomAula : horari.keySet()) {
            JSONObject aula = new JSONObject();

            // Afegim nova aula a l'array
            aula.put("nomAula", nomAula);
            Matriu m = horari.get(nomAula);

            JSONArray dies = new JSONArray();
            for (int i = 0; i < Enumeracio.Dia.values().length; i++) {
                JSONObject dia = new JSONObject();
                dia.put("dia", i);
                JSONArray hores = new JSONArray();
                    JSONArray caselles = new JSONArray();
                    for (int j = PlaEstudis.getHoraInici(); j < PlaEstudis.getHoraFi(); j++) {
                        if (m.getCasella(i,j) != null) {
                            Casella c = m.getCasella(i,j);
                            JSONObject hora = new JSONObject();
                            hora.put("hora", j);
                            hora.put("nomAssig", c.getNomAssig());
                            hora.put("numGrup", c.getNumGrup());
                            hora.put("tipus", c.getTipus().toString());
                            caselles.add(hora);
                        }
                    }
                hores.add(caselles);
                dia.put("hores", caselles);
                dies.add(dia);
            }
            aula.put("dies", dies);

            // Afegim aula a l'array general
            aules.add(aula);
        }

        // try-with-resources statement based on post comment below :)

        try {
            FileWriter file = new FileWriter("./Dades/Horaris/" + nomfitxer + ".json");
            file.write(aules.toJSONString());
            System.out.println("Horari Guardat");
            //System.out.println("\nJSON Object: " + aules);
            file.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void llegirHorari(String nomfitxer) {
        horari = new HashMap<>();

        JSONParser parser = new JSONParser();

        try {
            JSONArray arrayAules = (JSONArray) parser.parse(new FileReader("./Dades/Horaris/" + nomfitxer + ".json"));

            for (int z = 0; z < arrayAules.size(); z++) {

                Matriu m = new Matriu();

                JSONObject jsonAula = (JSONObject) arrayAules.get(z);

                JSONArray jsonDies = (JSONArray) jsonAula.get("dies");

                for (int i = 0; i < jsonDies.size(); i++) {
                    JSONObject jsonDia = (JSONObject) jsonDies.get(i);
                    JSONArray jsonHores = (JSONArray) jsonDia.get("hores");

                    for (int j = 0; j < jsonHores.size(); j++){
                        if (jsonHores != null) {
                                JSONObject jsonCasella = (JSONObject) jsonHores.get(j);
                                int numeroGrup = (int) (long) jsonCasella.get("numGrup");
                                String nomAssig = (String) jsonCasella.get("nomAssig");
                                String tipus = (String) jsonCasella.get("tipus");

                                Enumeracio.TipusSessio tipusS = Enumeracio.TipusSessio.TEORIA;

                                switch (tipus) {
                                    case "TEORIA" :
                                        tipusS = Enumeracio.TipusSessio.TEORIA;
                                        break;
                                    case "LABORATORI" :
                                        tipusS = Enumeracio.TipusSessio.LABORATORI;
                                        break;
                                    case "PROBLEMES" :
                                        tipusS = Enumeracio.TipusSessio.PROBLEMES;
                                        break;
                                }
                                Casella cas = new Casella();
                                cas.setNumGrup(numeroGrup);
                                cas.setNomAssig(nomAssig);
                                cas.setTipus(tipusS);
                                int hora = (int) (long) jsonCasella.get("hora");
                                m.assignarCasella(i,hora,cas);

                        }

                    }
                }
                String nomAula = (String) jsonAula.get("nomAula");
                horari.put(nomAula,m);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}