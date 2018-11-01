package Dominio;

import java.util.*;


public class Main {

    /////////////////////////////////////////////////////////////////
    /// MENÚ GESTIÓ CONFIGURACIÓ
    /////////////////////////////////////////////////////////////////

    public static void menuGestioConfiguracio(CapaDomini CD) {
        Scanner scanner = new Scanner(System.in);
        boolean on = true;
        int accio;

        while (on) {
            escriureMenuGestioConfiguracio();
            accio = scanner.nextInt();
            switch (accio) {
                case 1:
                    configuracioAules(CD);
                    break;
                case 2:
                    configuracioAssignatures(CD);
                    break;
                case 3:
                    on = false;
                    break;
                default:
                    escriureError(1);
                    break;
            }
        }
    }

        /////////////////////////////////////////////////////////////////
        /// AULES
        /////////////////////////////////////////////////////////////////

        // Gestió

        public static void configuracioAules(CapaDomini CD) {
            Scanner scanner = new Scanner(System.in);
            boolean on = true;
            int accio;

            while (on) {
                escriureMenuAula();
                accio = scanner.nextInt();
                switch (accio) {
                    case 1:
                        llistarAules(CD);
                        break;
                    case 2:
                        afegirAula(CD);
                        break;
                    case 3:
                        eliminarAula(CD);
                        break;
                    case 4:
                        on = false;
                        break;
                    default:
                        escriureError(1);
                        break;
                }
            }
        }

        // Operacions

        public static void llistarAules(CapaDomini CD) {

        }

        public static void afegirAula(CapaDomini CD) {
            /*Scanner scanner = new Scanner(System.in);

            System.out.println("ID de l'aula:");
            String id = scanner.nextLine();
            System.out.println("Capacitat de l'aula:");
            int capacitat = scanner.nextInt();

            System.out.println("Tipus de l'aula:");
            System.out.println("    Teoria      -> 1");
            System.out.println("    Laboratori  -> 2");

            Aula.TipusAula tipus = null;
            int numTipusAula = scanner.nextInt();

            if (numTipusAula == 1 || numTipusAula == 2) {
                tipus = numTipusAula == 1 ? Aula.TipusAula.TEORIA : Aula.TipusAula.LABORATORI;
            }
            */
        }

        public static void eliminarAula(CapaDomini CD) {

        }

        /////////////////////////////////////////////////////////////////
        /// ASSIGNATURES
        /////////////////////////////////////////////////////////////////

        // Gestió

        public static void configuracioAssignatures(CapaDomini CD) {
            Scanner scanner = new Scanner(System.in);
            boolean on = true;
            int accio;

            while (on) {
                escriureMenuAssignatura();
                accio = scanner.nextInt();
                switch (accio) {
                    case 1:
                        llistarAssignatures(CD);
                        break;
                    case 2:
                        afegirAssignatura(CD);
                        break;
                    case 3:
                        eliminarAssignatura(CD);
                        break;
                    case 4:
                        on = false;
                        break;
                    default:
                        escriureError(1);
                        break;
                }
            }
        }

        // Operacions

        public static void llistarAssignatures(CapaDomini CD) {

        }

        public static void afegirAssignatura(CapaDomini CD) {

        }

        public static void eliminarAssignatura(CapaDomini CD) {

        }


    /////////////////////////////////////////////////////////////////
    /// MENÚ GENERAR HORARI
    /////////////////////////////////////////////////////////////////

    public static void menuGenerarHorari(CapaDomini CD) {
        Scanner scanner = new Scanner(System.in);
        boolean on = true;
        int accio;

        while (on) {
            escriureMenuGenerarHorari();
            accio = scanner.nextInt();
            switch (accio) {
                case 1:
                    crearHorari(CD);
                    break;
                case 2:
                    llistarHorari(CD);
                    break;
                case 3:
                    eliminarHorari(CD);
                    break;
                case 4:
                    on = false;
                    break;
                default:
                    escriureError(1);
                    break;
            }
        }
    }

        /////////////////////////////////////////////////////////////////
        /// CREAR HORARI
        /////////////////////////////////////////////////////////////////

        // Gestió

        public static void crearHorari(CapaDomini CD) {

        }

        /////////////////////////////////////////////////////////////////
        /// LLISTAR HORARI
        /////////////////////////////////////////////////////////////////

        // Gestió

        public static void llistarHorari(CapaDomini CD) {

        }

        // Operacions

        /////////////////////////////////////////////////////////////////
        /// ELIMINAR HORARI
        /////////////////////////////////////////////////////////////////

        // Gestió

        public static void eliminarHorari(CapaDomini CD) {

        }

        // Operacions


    /////////////////////////////////////////////////////////////////
    /// MENÚ CONFIGURACIO
    /////////////////////////////////////////////////////////////////

    public static void menuConfiguracio(CapaDomini CD) {
        Scanner scanner = new Scanner(System.in);
        boolean on = true;
        int accio;

        while (on) {
            escriureMenuConfiguracio();
            accio = scanner.nextInt();
            switch (accio) {
                case 1:
                    intervalHores(CD);
                    break;
                case 2:
                    canviFranja(CD);
                    break;
                case 3:
                    on = false;
                    break;
                default:
                    escriureError(1);
                    break;
            }
        }
    }

        /////////////////////////////////////////////////////////////////
        /// INTERVAL HORES (HORA INICI / HORA FI)
        /////////////////////////////////////////////////////////////////

        // Gestió

        public static void intervalHores(CapaDomini CD) {

        }

            // Operacions GET

            public static void getHoraInici(CapaDomini CD) {

            }

            public static void getHoraFi(CapaDomini CD) {

            }

            // Operacions SET

            public static void setHoraInici(CapaDomini CD) {

            }

            public static void setHoraFi(CapaDomini CD) {

            }

        /////////////////////////////////////////////////////////////////
        /// CANVI FRANJA
        /////////////////////////////////////////////////////////////////

        // Gestió

        public static void canviFranja(CapaDomini CD) {

        }

        // Operacions GET

        public static void getHoraCF(CapaDomini CD) {

        }

        // Operacions SET

        public static void setHoraCF(CapaDomini CD) {

        }

    public static void main(String[] args) {
        CapaDomini CD = new CapaDomini();
        Scanner scanner = new Scanner(System.in);

        boolean on = true;
        int accio;

        while (on) {
            System.out.println("Indica el valor de l'operació:");
            escriureMenu();
            accio = scanner.nextInt();
            switch (accio) {
                case 1:
                    menuGestioConfiguracio(CD);
                    break;
                case 2:
                    menuGenerarHorari(CD);
                    break;
                case 3:
                    menuConfiguracio(CD);
                    break;
                case 4:
                    on = false;
                    break;
                default:
                    escriureError(1);
                    break;
            }
        }
    }

    // Nivell 1
    public static void escriureMenu() {
        System.out.println("1] Gestió Configuració");
        System.out.println("2] Generar Horari");
        System.out.println("3] Configuració");
        System.out.println("4] Sortir");
    }

    // Nivell 2 - [1] opció: Gestió Configuració
    public static  void escriureMenuGestioConfiguracio() {
        System.out.println("1] Configuració Aules");
        //System.out.println("2] Configuració Nivells");
        System.out.println("2] Configuració Assignatures");
        System.out.println("3] Sortir");
    }

    // Nivell 3 - [1 - 1] opcionó: Menú Aules
    public static void escriureMenuAula() {
        System.out.println("1] Llistar Aules Disponibles");
        System.out.println("2] Crear Aules");
        System.out.println("3] Eliminar Aules  ");
        System.out.println("4] Sortir");
    }

    // Nivell 3 - [1 - 3] opció: Menú Assignatures
    public static void escriureMenuAssignatura() {
        System.out.println("1] Llistar Assignatures Disponibles");
        System.out.println("2] Crear Assignatures");
        System.out.println("3] Eliminar Assignatures");
        System.out.println("4] Sortir");
    }

    // Nivell 2 - [2] opció: Generar Horari
    public static  void escriureMenuGenerarHorari() {
        System.out.println("1] Crear Horari");
        System.out.println("2] Llistar Horari");
        System.out.println("3] Eliminar Horari");
        System.out.println("4] Sortir");
    }

    // Nivell 2 - [3] opció: Configuració
    public static  void escriureMenuConfiguracio() {
        System.out.println("1] Hora Inici / Hora Fi");
        System.out.println("2] Hora Canvi Franja");
        System.out.println("3] Sortir");
    }

    // Nivell 3 - [3 - 1] opció: Hora Inici / Hora Fi

    public static  void escriureMenuInterval() {
        System.out.println("1] Ensenyar Hora [Inici - Fi]");
        System.out.println("2] Modificar Hora [Inici - Fi]");
        System.out.println("3] Sortir");
    }

    public static  void escriureMenuHoraCanviFranja() {
        System.out.println("1] Ensenyar Hora Cannvi Franja");
        System.out.println("2] Modificar Hora Cannvi Franja");
        System.out.println("3] Sortir");
    }

    public static void escriureError(int nError) {
        switch (nError){
            case 1:
                System.out.println("Error: Indica una de les opcions!");
                break;
        }
    }
}