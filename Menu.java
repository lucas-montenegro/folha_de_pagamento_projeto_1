import java.util.Scanner;

public class Menu { // TALVEZ MODIFICAR OS TIPOS DE TRABALHO E TIRAR OS COMISSIONADOS E VERIFICAR SE PAYROLL ESTÁ CORRETO
    public static void listOfEmployees(String [][] employees, int current_employees) {
        for(int i = 0; i < current_employees; i++) {
            System.out.printf("Nome: %s\n", employees[i][0]);
            System.out.printf("Id do funcionário: %s\n", employees[i][4]);
            if(employees[i][7].equals("0")) {
                System.out.printf("%s não pertence ao sindicato!\n", employees[i][0]);
            }
            else {
                System.out.printf("Id do sindicato do funcionário: %s\n\n", employees[i][8]);
            }
        }
    }

    public static void copy(String [][][] undo_redo, String [][] employees, int actual_index) {
        for(int i = 0; i < 50; i++) {
            employees[i][0] = undo_redo[actual_index][i][0];
            employees[i][1] = undo_redo[actual_index][i][1];
            employees[i][2] = undo_redo[actual_index][i][2];
            employees[i][3] = undo_redo[actual_index][i][3];
            employees[i][4] = undo_redo[actual_index][i][4];
            employees[i][5] = undo_redo[actual_index][i][5];
            employees[i][6] = undo_redo[actual_index][i][6];
            employees[i][7] = undo_redo[actual_index][i][7];
            employees[i][8] = undo_redo[actual_index][i][8];
            employees[i][9] = undo_redo[actual_index][i][9];
            employees[i][10] = undo_redo[actual_index][i][10];
            employees[i][11] = undo_redo[actual_index][i][11];
            employees[i][12] = undo_redo[actual_index][i][12];
        }
    }

    public static void updateModeOfPayment(String [][] employees, int [] schedule, int i) {
        if(employees[i][2].equals("1")) {
            employees[i][13] = "3";
            employees[i][14] = Integer.toString(schedule[3]);
        }
        else if(employees[i][2].equals("2") && !employees[i][5].equals("0")) {
            employees[i][13] = "2";
            employees[i][14] = Integer.toString(schedule[2]);
        }
        else if(employees[i][2].equals("2") && employees[i][5].equals("0")) {
            employees[i][13] = "1";
            employees[i][14] = Integer.toString(schedule[1]);
        }
    }


    public static void updatePayment(String [][] employees, int [][] calendary, int day_of_week, int day, int month, int i) {
        int days_to_payment = 0;
        int day_of_payment = Integer.parseInt(employees[i][14]);
        if(employees[i][13].equals("3")) {
            if(day_of_week % 7 == (day_of_payment + 1) % 7) {
                days_to_payment = 6 + 7;
            }
            else if(day_of_week % 7 == day_of_payment) {
                days_to_payment = 7;
            }
            else {
                days_to_payment = (5 - (day_of_week % 7)) + 7;
            }
        }
        else if(employees[i][13].equals("2")) {
            if(day_of_week % 7 == (day_of_payment + 1) % 7) {
                days_to_payment = 6 + 14;
            }
            else if(day_of_week % 7 == day_of_payment) {
                days_to_payment = 14;
            }
            else {
                days_to_payment = (5 - (day_of_week % 7)) + 14;
            }
        }
        else if(employees[i][13].equals("1")) {
            if(day_of_payment == -1) {
                days_to_payment = (calendary[month - 1][2] - day) + calendary[month][2];
            }
            else {
                days_to_payment = (calendary[month - 1][2] - day) + day_of_payment;
            }
        }

        employees[i][12] = Integer.toString(days_to_payment);
    }

    public static void createNewSchedule(int [] schedule) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o dia que será pago mensalmente, podendo escolher até o dia 28, caso deseje que o pagamento seja no último dia útil do mês digite -1:");
        int aux = input.nextInt();
        schedule[0] = aux;
        System.out.println("Digite o dia que será pago bi-semanalmente:");
        System.out.printf("(1) - Segunda\n(2) - Terça\n(3) - Quarta\n(4) - Quinta\n(5) - Sexta\n(6) - Sábado\n(7) - Domingo\n");
        aux = input.nextInt();
        schedule[1] = aux;
        System.out.println("Digite o dia que será pago semanalmente:");
        System.out.printf("(1) - Segunda\n(2) - Terça\n(3) - Quarta\n(4) - Quinta\n(5) - Sexta\n(6) - Sábado\n(7) - Domingo\n");
        aux = input.nextInt();
        schedule[2] = aux;
    }

    public static void chooseSchedule(String [][] employees, int [] schedule, int [][] calendary, int current_employees, int day_of_week, int day, int month) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o id do funcionário ou digite -1 para ver o número de todos os funcionários:");
        String option = input.nextLine();


        if(option.equals("-1")) {
            listOfEmployees(employees, current_employees);

            System.out.println("Digite o id do funcionário:");
            option = input.nextLine();
        }

        for(int i = 0; i < 50; i++){
            if(employees[i][4].equals(option)) {
                System.out.printf("Digite a agenda que o funcionário %s irá aderir:", employees[i][0]);
                System.out.printf("(1) - Mensalmente\n(2) - Bi-Semanalmente\n(3) - Semanalmente\n");
                option = input.nextLine();
                employees[i][13] = option;
                int option_int = Integer.parseInt(option);
                if(option.equals("1")) {
                    employees[i][14] = Integer.toString(schedule[option_int]);
                    updatePayment(employees, calendary, day_of_week, day, month, i);
                }
                else if(option.equals("2")) {
                    employees[i][14] = Integer.toString(schedule[option_int]);
                    updatePayment(employees, calendary, day_of_week, day, month, i);
                }
                else if(option.equals("3")) {
                    employees[i][14] = Integer.toString(schedule[option_int]);
                    updatePayment(employees, calendary, day_of_week, day, month, i);
                }

                break;
            }
        }
    }


    public static int undoRedo(String [][][] undo_redo, String [][] employees, int actual_index, int max_index) {
        Scanner input = new Scanner(System.in);
        System.out.printf("(1) - Parar\n(2) - Redo\n(3) - Undo\n");
        int option = input.nextInt();

        if(actual_index == -1) {
            actual_index = max_index;
        }

        while(option != 1) {
            if(option == 2) {
                if(actual_index < max_index) {
                    actual_index++;
                    System.out.println("Redo realizado com sucesso!");
                }
                else {
                    System.out.println("Redo não pôde ser realizado!");
                }
            }
            else if(option == 3){
                if(actual_index > 1) {
                    actual_index--;
                    System.out.println("Undo realizado com sucesso!");
                }
                else {
                    System.out.println("Undo não pôde ser realizado!");
                }
            }

            System.out.printf("(1) - Parar\n(2) - Redo\n(3) - Undo\n");
            option = input.nextInt();
        }

        copy(undo_redo, employees, actual_index);
        return actual_index;
    }


    public static void payEmployee(String [][] employees, int i, double actual_salary) {
        double total;
        System.out.printf("Sálario atual: %.2f\n", actual_salary);

        if(!employees[i][10].equals("0")) {
            double taxes = Double.parseDouble(employees[i][10]);
            total = (actual_salary * taxes) / 100.0;
            actual_salary = actual_salary - total;
            System.out.printf("-%.2f de serviços do sindicato\n", total);
            employees[i][10] = "0";
        }

        employees[i][6] = Double.toString(actual_salary);
        System.out.println("----------------------------------");
        System.out.printf("Foi pago %.2f\n", actual_salary);
    }


    public static void payroll(String [][] employees, int current_employees, int day, int month) { // adicionar um metodo de tirar a taxa do sindicato em um dia do mes para todo mundo
        for(int i = 0; i < current_employees; i++) {
            double actual_salary = Double.parseDouble(employees[i][6]);
            double taxes = 0;
            double total;
            int days_to_payment; // updates the quantity of days to the next payment

            if(day == 1) { // syndicate´s day of payment
                if(employees[i][7].equals("1")) {
                    taxes = Double.parseDouble(employees[i][9]);
                    total = (actual_salary * taxes) / 100.0;
                    actual_salary = actual_salary - total;
                    System.out.printf("O funcionário %s, de id de sindicato %s, teve descontado %.2f de seu salário pela sua participação no sindicato!\n", employees[i][0], employees[i][8], total);
                    employees[i][6] = Double.toString(actual_salary);
                }
            }

            if(employees[i][12].equals("0")) {
                System.out.printf("Nome: %s | ID : %s\n\n", employees[i][0], employees[i][4]);
                if(employees[i][3].equals("1")) {
                    payEmployee(employees, i, actual_salary);
                    employees[i][12] = "7";
                    employees[i][6] = "0";
                }
                else if(employees[i][3].equals("3") && employees[i][5].equals("0")) {
                    payEmployee(employees, i, actual_salary);
                    if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                        employees[i][12] = "31";
                    }
                    else if(month == 2) {
                        employees[i][12] = "28";
                    }
                    else if(month == 4 || month == 6 || month == 9 || month == 11) {
                        employees[i][12] = "30";
                    }
                    employees[i][6] = employees[i][3];
                }
                else if(employees[i][3].equals("3") && !employees[i][5].equals("0")) {
                    actual_salary += Double.parseDouble(employees[i][3]) / 2.0;
                    payEmployee(employees, i, actual_salary);
                    employees[i][12] = "14";
                    employees[i][6] = "0";
                }

                if(employees[i][11].equals("1")) {
                    System.out.printf("Recebeu o pagamento através de um cheque para %s\n", employees[i][1]);
                }
                else if(employees[i][11].equals("2")) {
                    System.out.println("Recebeu o pagamento através de um cheque em mãos");
                }
                else if(employees[i][11].equals("3")) {
                    System.out.println("Recebeu o pagamento através de um depósito na conta bancária");
                }
            }
            else {
                days_to_payment = Integer.parseInt(employees[i][12]) - 1;
                employees[i][12] = Integer.toString(days_to_payment);
                System.out.printf("O funcionário %s não recebeu nenhum pagamento hoje\n", employees[i][0]);
            }
        }
    }

    public static void changeData(String [][] employees, int current_employees, int day_of_week, int day, int month, int [][] calendary, int [] schedule) { // talvez tenha que alterar !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o id do funcionário ou digite -1 para ver o número de todos os funcionários:");
        String option = input.nextLine();


        if(option.equals("-1")) {
            listOfEmployees(employees, current_employees);

            System.out.println("Digite o id do funcionário:");
            option = input.nextLine();
        }

        for(int i = 0; i < 50; i++) {
            if(employees[i][4].equals("option")) {
                System.out.println("Digite 1 para modificar o nome e 0 para não modificar");
                option = input.nextLine();
                if(option.equals("1")) {
                    System.out.println("Digite o nome:");
                    String name = input.nextLine();
                    employees[i][0] = name;
                }

                System.out.println("Digite 1 para modificar o endereço e 0 para não modificar");
                option = input.nextLine();
                if(option.equals("1")) {
                    System.out.println("Digite o endereço:");
                    String address = input.nextLine();
                    employees[i][1] = address;
                }

                System.out.println("Digite 1 para modificar o tipo de trabalho e 0 para não modificar");
                option = input.nextLine();
                if(option.equals("1")) {
                    System.out.println("(1) Horista\n(2) Salariado");
                    String type = input.nextLine();
                    if(!employees[i][2].equals(type)) {
                        employees[i][2] = type;
                        updateModeOfPayment(employees, schedule, i);
                        updatePayment(employees, calendary, day_of_week, day, month, i);
                    }
                }

                System.out.println("Digite 1 para modificar a comissão e 0 para não modificar");
                option = input.nextLine();
                if(option.equals("1")) {
                    System.out.println("Digite a comissão do funcionário, caso não exista digite 0:");
                    String commission = input.nextLine();
                    if((employees[i][5].equals("0") && !commission.equals("0")) || (!employees[i][5].equals("0") && commission.equals("0"))) {
                        employees[i][5] = commission;
                        updateModeOfPayment(employees, schedule, i);
                        updatePayment(employees, calendary, day_of_week, day, month, i);
                    }
                }

                System.out.println("Digite 1 para modificar o salário e 0 para não modificar");
                option = input.nextLine();
                if(option.equals("1")) {
                    System.out.println("Digite o novo salário:");
                    String salary = input.nextLine();
                    employees[i][3] = salary;

                    if(employees[i][2].equals("2") && employees[i][5].equals("0")) {

                    }
                }

                System.out.println("Digite 1 para modificar o id e 0 para não modificar");
                option = input.nextLine();
                if(option.equals("1")) {
                    System.out.println("Digite o novo id(número natural válido):");
                    String new_id = input.nextLine();
                    for(int j = 0; j < 50; j++) {
                        if(employees[j][4].equals(new_id) && j != i) {
                            System.out.println("Digite um id válido(digite um id inexistente):");
                            new_id = input.nextLine();
                            i = 0;
                        }
                    }
                    employees[i][4] = new_id;
                }

                System.out.println("Digite 1 para modificar a quantia do próximo contracheque e 0 para não modificar");
                option = input.nextLine();
                if(option.equals("1")) {
                    System.out.println("Digite o valor do novo contracheque:");
                    String atual_salary = input.nextLine();
                    employees[i][6] = atual_salary;
                }

                System.out.println("Digite 1 para modificar a participação no sindicato e 0 para não modificar");
                option = input.nextLine();
                if(option.equals("1")) {
                    System.out.println("Digite 1 para participar e 0 para não participar:");
                    String syndicate = input.nextLine();
                    employees[i][7] = syndicate;

                    if(syndicate.equals("0")) {
                        System.out.println("Como você saiu do sindicato o seu id e a sua taxa serão removidos automaticamente.");
                        employees[i][8] = "-1";
                        employees[i][9] = "0";
                    }
                }

                System.out.println("Digite 1 para modificar a identificação no sindicato e 0 para não modificar");
                option = input.nextLine();
                if(option.equals("1")) {
                    System.out.println("Digite a identificação do sindicato(número natural válido):");
                    String id_syndicate = input.nextLine();
                    for(int j = 0; j < 50; j++) {
                        if(employees[j][8].equals(id_syndicate) && j != i) {
                            System.out.println("Digite um id de sindicato válido(id de sindicato já existente):");
                            id_syndicate = input.nextLine();
                            i = 0;
                        }
                    }
                    employees[i][8] = id_syndicate;
                }

                System.out.println("Digite 1 para modificar a taxa do sindicato e 0 para não modificar");
                option = input.nextLine();
                if(option.equals("1")) {
                    System.out.println("Digite a taxa do sindicato:");
                    String tax_syndicate = input.nextLine();
                    employees[i][9] = tax_syndicate;
                }

                System.out.println("Digite 1 para modificar a taxa de serviço do sindicato e 0 para não modificar");
                option = input.nextLine();
                if(option.equals("1")) {
                    System.out.println("Digite a taxa de serviço do sindicato:");
                    String tax_service = input.nextLine();
                    employees[i][10] = tax_service;
                }

                System.out.println("Digite 1 para modificar o metódo de pagamento e 0 para não modificar");
                option = input.nextLine();
                if(option.equals("1")) {
                    System.out.println("(1) Receber o pagamento em cheque pelos correios\n(2) Receber o pagamento em cheque em mãos\n(3) Receber o pagamento na conta bancária");
                    String payment = input.nextLine();
                    employees[i][11] = payment;
                }

                System.out.println("Digite 1 para alterar a quantidade de dias para o pagamento e 0 para não modificar"); // retirar !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                option = input.nextLine();
                if(option.equals("1")) {
                    System.out.println("Digite a quantidade de dias para o pagamento:");
                    String days_to_payment = input.nextLine();
                    employees[i][12] = days_to_payment;
                }
            }
        }
    }

    public static void addServiceTax(String [][] employees, int current_employees) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o id do sindicato do funcionário ou digite -1 para ver o número de todos os funcionários:");
        String option = input.nextLine();

        if(option.equals("-1")) {
            listOfEmployees(employees, current_employees);

            System.out.println("Digite o id do sindicato do funcionário:");
            option = input.nextLine();
        }

        for(int i = 0; i < 50; i++){
            if(employees[i][8].equals(option)) {
                System.out.println("Digite o nome do serviço:");
                String service_name = input.nextLine();
                System.out.println("Digite a taxa do serviço");
                String service_tax = input.nextLine();
                employees[i][10] = service_tax;

                break;
            }
        }
    }

    public static void addSale(String [][] employees, int current_employees) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o id do funcionário ou digite -1 para ver o número de todos os funcionários:");
        String option = input.nextLine();


        if(option.equals("-1")) {
            listOfEmployees(employees, current_employees);

            System.out.println("Digite o id do funcionário:");
            option = input.nextLine();
        }

        for(int i = 0; i < 50; i++){
            if(employees[i][4].equals(option)) {
                System.out.printf("Digite o valor da venda que o funcionário %s, com o id %s, realizou hoje:\n", employees[i][0], employees[i][4]);
                String sale = input.nextLine();
                System.out.println("Digite a data da venda(dia/mês/ano):");
                String date = input.nextLine();
                double sale_double = Double.parseDouble(sale);
                double actual_salary = Double.parseDouble(employees[i][7]);
                double commission = Double.parseDouble(employees[i][6]);
                double total = actual_salary + ((commission * sale_double) / 100);
                String total_string = Double.toString(total);
                employees[i][6] = total_string;

                break;
            }
        }
    }

    public static void addHoursWorked(String [][] employees, int current_employees) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o id do funcionário ou digite -1 para ver o número de todos os funcionários:");
        String option = input.nextLine();


        if(option.equals("-1")) {
            listOfEmployees(employees, current_employees);

            System.out.println("Digite o id do funcionário:");
            option = input.nextLine();
        }

        for(int i = 0; i < 50; i++){
            if(employees[i][4].equals(option)) {
                System.out.printf("Digite a hora de entrada do funcionário %s:\n", employees[i][4]);
                int entry_hour = input.nextInt();
                System.out.printf("Digite o minuto de entrada do funcionário %s:\n", employees[i][4]);
                int entry_minute = input.nextInt();
                System.out.printf("Digite a hora de saída do funcionário %s:\n", employees[i][4]);
                int exit_hour = input.nextInt();
                System.out.printf("Digite o minuto de entrada do funcionário %s:\n", employees[i][4]);
                int exit_minute = input.nextInt();

                int total_hours;

                if(exit_hour == entry_hour) {
                    total_hours = 24;
                }
                else if(entry_hour > exit_hour) {
                    total_hours = 24 - entry_hour + exit_hour;
                }
                else {
                    total_hours = exit_hour - entry_hour;
                }

                if(entry_minute > exit_minute) {
                    total_hours--;
                }

                if(employees[i][2].equals("1")) {
                    double actual_salary = Double.parseDouble(employees[i][7]);
                    double salary = Double.parseDouble(employees[i][3]);

                    if(total_hours > 8) {
                        actual_salary += salary * 8 + (salary * 1.5 * (total_hours - 8));
                    }
                    else {
                        actual_salary += salary * total_hours;
                    }

                    employees[i][6] = Double.toString(actual_salary);
                }

                break;
            }
        }
    }

    public static void remove(String [][] employees, int position, int current_employees) {
        if(position == 0 && current_employees == 1) {
            return;
        }

        for(int i = position; i < current_employees; i++) {
            employees[i][0] = employees[i + 1][0];
            employees[i][1] = employees[i + 1][1];
            employees[i][2] = employees[i + 1][2];
            employees[i][3] = employees[i + 1][3];
            employees[i][4] = employees[i + 1][4];
            employees[i][5] = employees[i + 1][5];
            employees[i][6] = employees[i + 1][6];
            employees[i][7] = employees[i + 1][7];
            employees[i][8] = employees[i + 1][8];
            employees[i][9] = employees[i + 1][9];
            employees[i][10] = employees[i + 1][10];
            employees[i][11] = employees[i + 1][11];
            employees[i][12] = employees[i + 1][12];
        }
    }

    public static void removeEmployee(String [][] employees, int current_employees) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o id do funcionário que deseja remover ou digite -1 para ver o número de todos os funcionários:");
        String option = input.nextLine();


        if(option.equals("-1")) {
            listOfEmployees(employees, current_employees);

            System.out.println("Digite o id do funcionário que deseja remover:");
            option = input.nextLine();
        }

        for(int i = 0; i < current_employees; i++){
            if(employees[i][4].equals(option)) {
                remove(employees, i, current_employees);
                break;
            }
        }
    }

    public static void addEmployee(String [][] employees, int current_employees, int id, int id_syndicate, int day_of_week, int day, int month, int [][] calendary, int [] schedule) {
        Scanner input = new Scanner(System.in);

        System.out.println("Digite o nome do funcionário:");
        String name = input.nextLine();
        System.out.println();

        System.out.println("Digite o endereço do funcionário:");
        String address = input.nextLine();
        System.out.println();

        System.out.println("(1) Horista\n(2) Assalariado");
        String type = input.nextLine();
        System.out.println();

        System.out.println("Digite o salário do funcionário:");
        String salary = input.nextLine();
        System.out.println();

        System.out.println("(1) Receber o pagamento em cheque pelos correios\n(2) Receber o pagamento em cheque em mãos\n(3) Receber o pagamento na conta bancária");
        String payment = input.nextLine();
        System.out.println();

        System.out.println("Digite a taxa de comissão do funcionário, caso não exista digite 0:");
        String commission = input.nextLine();
        System.out.println();

        System.out.println("Digite 0 caso não pertença ao sindicato e 1 caso pertença:");
        String belongs_to_syndicate = input.nextLine();
        System.out.println();

        String id_string = Integer.toString(id);
        String id_syndicate_string = Integer.toString(id_syndicate);

        for(int i = 0; i < 50; i++) {
            if (i == current_employees) {
                employees[i][0] = name;
                employees[i][1] = address;
                employees[i][2] = type;
                employees[i][3] = salary;
                employees[i][4] = id_string;
                employees[i][5] = commission;
                employees[i][6] = "0";
                employees[i][7] = belongs_to_syndicate;
                employees[i][8] = "-1";
                employees[i][9] = "0";
                employees[i][10] = "0";
                employees[i][11] = payment;
                employees[i][12] = "0";

                if(employees[i][2].equals("3")) {
                    employees[i][6] = employees[i][3];
                }

                if(employees[i][7].equals("1")) {
                    System.out.println("Digite a taxa do sindicato:");
                    String tax_syndicate = input.nextLine();
                    System.out.println();

                    employees[i][8] = id_syndicate_string;
                    employees[i][9] = tax_syndicate;
                }

                updateModeOfPayment(employees, schedule, i);
                updatePayment(employees, calendary, day_of_week, day, month, i);
                break;
            }
        }
    }

    public static void calculateCalendary(int [][] calendary, int initial_day) {
        for(int i = 0; i < 12; i++) {
            if (i == 0 || i == 2 || i == 4 || i == 6 || i == 7 || i == 9 || i == 11) {
                calendary[i][0] = initial_day;
                calendary[i][2] = 31;

                if ((initial_day + 30) % 7 <= 5 && (initial_day + 30) % 7 > 0) {
                    calendary[i][1] = 31;
                } else if ((initial_day + 29) % 7 <= 5 && (initial_day + 29) % 7 > 0) {
                    calendary[i][1] = 30;
                } else if ((initial_day + 28) % 7 <= 5 && (initial_day + 28) % 7 > 0) {
                    calendary[i][1] = 29;
                }
                initial_day = (initial_day + 31) % 7;
            }
            else if (i == 1) {
                calendary[i][0] = initial_day;
                calendary[i][2] = 28;

                if ((initial_day + 27) % 7 <= 5 && (initial_day + 27) % 7 > 0) {
                    calendary[i][1] = 28;
                } else if ((initial_day + 26) % 7 <= 5 && (initial_day + 26) % 7 > 0) {
                    calendary[i][1] = 27;
                } else if ((initial_day + 25) % 7 <= 5 && (initial_day + 25) % 7 > 0) {
                    calendary[i][1] = 26;
                }
                initial_day = (initial_day + 28) % 7;
            }
            else if (i == 3 || i == 5 || i == 8 || i == 10) {
                calendary[i][0] = initial_day;
                calendary[i][2] = 30;

                if ((initial_day + 29) % 7 <= 5 && (initial_day + 29) % 7 > 0) {
                    calendary[i][1] = 30;
                } else if ((initial_day + 28) % 7 <= 5 && (initial_day + 28) % 7 > 0) {
                    calendary[i][1] = 29;
                } else if ((initial_day + 27) % 7 <= 5 && (initial_day + 27) % 7 > 0) {
                    calendary[i][1] = 28;
                }
                initial_day = (initial_day + 30) % 7;
            }
        }
    }

    public static void updateUndoRedo(String [][] employees, String [][][] undo_redo, int max_index) {
        if(max_index == 1000) {
            System.out.println("A matriz de undo/redo atingiu o limite! Não pode mais armazenar as matrizes de funcionários");
        }
        else {
            for(int i = 0; i < 50; i++) {
                undo_redo[max_index][i][0] = employees[i][0];
                undo_redo[max_index][i][1] = employees[i][1];
                undo_redo[max_index][i][2] = employees[i][2];
                undo_redo[max_index][i][3] = employees[i][3];
                undo_redo[max_index][i][4] = employees[i][4];
                undo_redo[max_index][i][5] = employees[i][5];
                undo_redo[max_index][i][6] = employees[i][6];
                undo_redo[max_index][i][7] = employees[i][7];
                undo_redo[max_index][i][8] = employees[i][8];
                undo_redo[max_index][i][9] = employees[i][9];
                undo_redo[max_index][i][10] = employees[i][10];
                undo_redo[max_index][i][11] = employees[i][11];
                undo_redo[max_index][i][12] = employees[i][12];
                undo_redo[max_index][i][13] = employees[i][13];
                undo_redo[max_index][i][14] = employees[i][14];
            }
        }
    }

    public static void initializeMatrix(String [][] employees, String [][][] undo_redo) {
        for(int i = 0; i < 50; i++) {
            employees[i][0] = null;
            employees[i][1] = null;
            employees[i][2] = null;
            employees[i][3] = null;
            employees[i][4] = null;
            employees[i][5] = null;
            employees[i][6] = null;
            employees[i][7] = null;
            employees[i][8] = null;
            employees[i][9] = null;
            employees[i][10] = null;
            employees[i][11] = null;
            employees[i][12] = null;
            employees[i][13] = null;
            employees[i][14] = null;
        }

        for(int j = 0; j < 1000; j++) {
            for(int k = 0; k < 50; k++) {
                undo_redo[j][k][0] = null;
                undo_redo[j][k][1] = null;
                undo_redo[j][k][2] = null;
                undo_redo[j][k][3] = null;
                undo_redo[j][k][4] = null;
                undo_redo[j][k][5] = null;
                undo_redo[j][k][6] = null;
                undo_redo[j][k][7] = null;
                undo_redo[j][k][8] = null;
                undo_redo[j][k][9] = null;
                undo_redo[j][k][10] = null;
                undo_redo[j][k][11] = null;
                undo_redo[j][k][12] = null;
                undo_redo[j][k][13] = null;
                undo_redo[j][k][14] = null;
            }
        }
    }

    public static int updateCurrentEmployee(String [][] employees) {
        int current_employees = 0;
        for(int i = 0; i < 50; i++) {
            if(employees[i][0] == null) {
                break;
            }
            current_employees++;
        }

        return current_employees;
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String [][] employees = new String[50][15];
        String [][][] undo_redo = new String[1000][50][15];
        int [] schedule = new int[3];
        int [][] calendary = new int[12][3];
        int option, id = 1, id_syndicate = 1000, current_employees = 0, day = 0, day_of_week = 0, month = 0, year = 0, initial_day;
        int actual_index = -1, max_index = 0;
        initializeMatrix(employees, undo_redo);
        // inicialização da agenda de pagamento:
        schedule[0] = -1; // último dia útil do mês
        schedule[1] = 5; // bi-semanalmente pago toda sexta
        schedule[2] = 5; // semanalmente pago toda sexta
        // -----------------------------------------------
        max_index++;

        System.out.println("Bem vindo ao sistema de Folha de Pagamento!");
        System.out.printf("Agora vamos configurar o sistema!\n\n");
        System.out.println("Digite o dia da semana do primeiro dia do ano:");
        System.out.printf("(1) - Segunda\n(2) - Terça\n(3) - Quarta\n(4) - Quinta\n(5) - Sexta\n(6) - Sábado\n(7) - Domingo\n");
        initial_day = input.nextInt();
        calculateCalendary(calendary, initial_day);

        System.out.println("Digite o dia da semana da data atual:");
        System.out.printf("(1) - Segunda\n(2) - Terça\n(3) - Quarta\n(4) - Quinta\n(5) - Sexta\n(6) - Sábado\n(7) - Domingo\n");
        day_of_week = input.nextInt();
        System.out.println("Digite o dia do mês da data atual:");
        day = input.nextInt();
        System.out.printf("(1) - Janeiro\n(2) - Fevereiro\n(3) - Março\n(4) - Abril\n(5) - Maio\n(6) - Junho\n(7) - Julho\n(8) - Agosto\n(9) - Setembro\n(10) - Outubro\n(11) - Novembro\n(12) - Dezembro\n");
        month = input.nextInt();
        System.out.println("Digite o ano da data atual:");
        year = input.nextInt();

        while(true) {
            System.out.println("Digite uma das opções abaixo para executar o programa:");
            System.out.printf("\n0 - Parar o programa\n1 - Adicionar um funcionário\n2 - Remover um funcionário\n3 - Lançar um cartão de ponto\n4 - Adicionar venda\n5 - Adicionar uma taxa de serviço\n6 - Alterar dados de um funcionário\n7 - Rodar o sistema de pagamentos\n8 - Undo/Redo\n\n");
            System.out.println("Digite uma das opções:");
            option = input.nextInt();
            System.out.println();

            while(option != 0 && option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7 && option != 8 && option != 9 && option != 10) {
                System.out.println("Digite uma opção válida:");
                option = input.nextInt();
            }

            if(option == 0) {
                break;
            }
            else if(option == 1) {
                addEmployee(employees, current_employees, id, id_syndicate, day_of_week, day, month, calendary, schedule);
                System.out.println("Funcionário adicionado com sucesso!");
                current_employees++;
                id++;
                id_syndicate++;
                updateUndoRedo(employees, undo_redo, max_index);
                max_index++;
            }
            else if(option == 2) {
                removeEmployee(employees, current_employees);
                System.out.println("Funcionário removido com sucesso!");
                current_employees--;
                updateUndoRedo(employees, undo_redo, max_index);
                max_index++;
            }
            else if(option == 3) {
                addHoursWorked(employees, current_employees);
                System.out.println("Cartão de ponto adicionado com sucesso!");
                updateUndoRedo(employees, undo_redo, max_index);
                max_index++;
            }
            else if(option == 4) {
                addSale(employees, current_employees);
                System.out.println("Venda adicionada com sucesso!");
                updateUndoRedo(employees, undo_redo, max_index);
                max_index++;
            }
            else if(option == 5) {
                addServiceTax(employees, current_employees);
                System.out.println("Serviço adicionado com sucesso!");
                updateUndoRedo(employees, undo_redo, max_index);
                max_index++;
            }
            else if(option == 6) {
                changeData(employees, current_employees, day_of_week, day, month, calendary, schedule);
                System.out.println("Dado(s) modificado(s) com sucesso!");
                updateUndoRedo(employees, undo_redo, max_index);
                max_index++;
            }
            else if(option == 7) {
                System.out.println("Rodar a folha de pagamento irá passar o dia!");
                payroll(employees, current_employees, day, month);
                System.out.printf("Folha de pagamento realizada na data %d/%d/%d !\n", day, month, year);
                day++;
                day_of_week++;

                if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                    if(day == 32) {
                        day = 1;
                        month++;
                    }
                }
                else if (month == 2) {
                    if(day == 29) {
                        day = 1;
                        month++;
                    }
                }
                else if (month == 4 || month == 6 || month == 9 || month == 11) {
                    if(day == 31) {
                        day = 1;
                        month++;
                    }
                }

                if(month == 13) {
                    calculateCalendary(calendary, day_of_week);
                    day = 1;
                    month = 1;
                    year++;
                }
            }
            else if(option == 8) {
                actual_index = undoRedo(undo_redo, employees, actual_index, max_index);
            }
            else if(option == 9) {
                System.out.println("Escolher uma nova agenda irá reiniciar os dias restantes para o próximo pagamento do funcionário");
                chooseSchedule(employees, schedule, calendary, current_employees, day_of_week, day, month);
            }
            else if(option == 10) {
                createNewSchedule(schedule);
            }

            System.out.printf("\n-------------------------------------------------------\n\n");
             current_employees = updateCurrentEmployee(employees);
        }

        for(int i = 0; i < current_employees; i++) {
            System.out.printf("Nome: %s\n", employees[i][0]);
            System.out.printf("Tipo: %s\n", employees[i][2]);
            System.out.printf("Salário Atual: %s\n", employees[i][6]);
            System.out.printf("Sindicato: %s\n", employees[i][7]);
            System.out.printf("Taxa sindicato: %s\n", employees[i][9]);
            System.out.printf("Id sindicato: %s\n\n", employees[i][8]);
        }

        for(int i = 0; i < 12; i++) {
            System.out.printf("Mês: %d\n", i + 1);
            System.out.printf("Dia inicial: %d\n", calendary[i][0]);
            System.out.printf("Último dia útil: %d\n\n", calendary[i][1]);
        }

        System.out.println("Obrigado por utilizar o nosso sistema!");
    }
}

/* MATRIZ employees(colunas):
   0 -> nome
   1 -> endereço
   2 -> tipo
   3 -> salário
   4 -> id
   5 -> porcentagem da comissao
   6 -> salário atual
   7 -> pertence sindicato
   8 -> id no sindicato
   9 -> taxa do sindicato
   10 -> taxa do servico
   11 -> metodo de pagamento
   12 -> dias para o pagamento
   13 -> modo de pagamento(mensalmente, bi-semanalmente, semanalmente)
   14 -> dia que é pago(do mês ou da semana)

   MATRIZ calendary(colunas):
   0 -> dia inicial do mês (segunda, terça...)
   1 -> último dia útil do mês (28, 29...)
   2 -> quantidade de dias do mês

   ARRAY schedule(colunas):
   0 -> Dia pago mensalmente(1, 2, 3, 17, 18...)
   1 -> Dia pago bi-semanalmente(1(segunda), 2(terça), 3(quarta)...)
   2 -> Dia pago semanalmente(1(segunda), 2(terça), 3(quarta)...)

   Obs: Em relação ao dia, temos:
   day_of_week % 7 = 1 -> Segunda
   day_of_week % 7 = 2 -> Terça
   day_of_week % 7 = 3 -> Quarta
   day_of_week % 7 = 4 -> Quinta
   day_of_week % 7 = 5 -> Sexta
   day_of_week % 7 = 6 -> Sábado
   day_of_week % 7 = 0 -> Domingo

   Obs2: A taxa do sindicato é retirada no último dia útil do mês de todos que fazem parte do sindicato

   Obs3: A quantidade máxima de undo/redo que será realizado nesse programa é 1000 vezes
 */
