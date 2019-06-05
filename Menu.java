import java.util.Scanner;

public class Menu {
    public static void printar_dados(String[][] employees, int i) {
        System.out.printf("Nome: %s\n", employees[i][0]);
        System.out.printf("Endereço: %s\n", employees[i][1]);
        System.out.printf("Tipo: %s\n", employees[i][2]);
        System.out.printf("Salário: %s\n", employees[i][3]);
        System.out.printf("Id do funcionário: %s\n", employees[i][4]);
        System.out.printf("Comissão: %s\n", employees[i][5]);
        System.out.printf("Salário atual: %s\n", employees[i][6]);
        if (!employees[i][7].equals("1")) {
            System.out.printf("Não pertence ao sindicato! ID falho: %s\n", employees[i][8]);
        } else {
            System.out.printf("Id do sindicato do funcionário: %s\n", employees[i][8]);
        }
        System.out.printf("Taxa do sindicato: %s\n", employees[i][9]);
        System.out.printf("Taxa de serviço: %s\n", employees[i][10]);
        System.out.printf("Método de pagamento: %s\n", employees[i][11]);
        System.out.printf("Dias para o pagamento: %s\n", employees[i][12]);
        System.out.printf("Tipo de agenda: %s\n", employees[i][13]);
        System.out.printf("Dia de pagamento da agenda: %s\n", employees[i][14]);
        System.out.println();
    }

    public static void listOfEmployees(String[][] employees, int current_employees) {
        for (int i = 0; i < current_employees; i++) {
            System.out.printf("Nome: %s\n", employees[i][0]);
            System.out.printf("Id do funcionário: %s\n", employees[i][4]);
            if (!employees[i][7].equals("1")) {
                System.out.printf("%s não pertence ao sindicato!\n\n", employees[i][0]);
            } else {
                System.out.printf("Id do sindicato do funcionário: %s\n\n", employees[i][8]);
            }
        }
    }

    public static void copy(String[][][] undo_redo, String[][] employees, int actual_index) {
        for (int i = 0; i < 50; i++) {
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
            employees[i][13] = undo_redo[actual_index][i][13];
            employees[i][14] = undo_redo[actual_index][i][14];
        }
    }

    public static void updateModeOfPayment(String[][] employees, int[] schedule, int i) {
        if (employees[i][2].equals("1")) {
            employees[i][13] = "3";
            employees[i][14] = Integer.toString(schedule[2]);
        } else if (employees[i][2].equals("2") && !employees[i][5].equals("0")) {
            employees[i][13] = "2";
            employees[i][14] = Integer.toString(schedule[1]);
        } else if (employees[i][2].equals("2") && employees[i][5].equals("0")) {
            employees[i][13] = "1";
            employees[i][14] = Integer.toString(schedule[0]);
        }
    }

    public static void updateAllPayment(String[][] employees, int[][] calendary, int day_of_week, int day, int month, int year) {
        int days_to_payment, day_of_payment;
        for (int i = 0; i < 50; i++) {
            if (employees[i][0] != null) {
                days_to_payment = 0;
                day_of_payment = Integer.parseInt(employees[i][14]) % 7;
                if (employees[i][13].equals("3")) {
                    if (day_of_week % 7 == day_of_payment % 7) {
                        days_to_payment = 7;
                    } else if ((day_of_week % 7) > (day_of_payment % 7)) {
                        days_to_payment = (day_of_payment - (day_of_week % 7)) + 14;
                    } else if ((day_of_week % 7) < (day_of_payment % 7)) {
                        days_to_payment = (day_of_payment - (day_of_week % 7)) + 7;
                    }
                } else if (employees[i][13].equals("2")) {
                    if (day_of_week % 7 == day_of_payment % 7) {
                        days_to_payment = 14;
                    } else if ((day_of_week % 7) > (day_of_payment % 7)) {
                        days_to_payment = (day_of_payment - (day_of_week % 7)) + 21;
                    } else if ((day_of_week % 7) < (day_of_payment % 7)) {
                        days_to_payment = (day_of_payment - (day_of_week % 7)) + 14;
                    }
                } else if (employees[i][13].equals("1")) {
                    if (month < 12) {
                        if (day_of_payment == -1) {
                            days_to_payment = (calendary[month - 1][2] - day) + calendary[month][1];
                        } else {
                            days_to_payment = (calendary[month - 1][2] - day) + day_of_payment;
                        }
                    } else {
                        int aux_day_of_week = day_of_week + (31 - day) + 1;
                        calculateCalendary(calendary, aux_day_of_week, year);
                        if (day_of_payment == -1) {
                            days_to_payment = (31 - day) + calendary[1][1];
                        } else {
                            days_to_payment = (31 - day) + day_of_payment;
                        }
                        calculateCalendary(calendary, aux_day_of_week - 365, year);
                    }
                }
                employees[i][12] = Integer.toString(days_to_payment);
            }
        }
    }

    public static void updatePayment(String[][] employees, int[][] calendary, int day_of_week, int day, int month, int year, int i) {
        int days_to_payment = 0;
        int day_of_payment = Integer.parseInt(employees[i][14]) % 7;
        if (employees[i][13].equals("3")) {
            if (day_of_week % 7 == day_of_payment % 7) {
                days_to_payment = 7;
            } else if ((day_of_week % 7) > (day_of_payment % 7)) {
                days_to_payment = (day_of_payment - (day_of_week % 7)) + 14;
            } else if ((day_of_week % 7) < (day_of_payment % 7)) {
                days_to_payment = (day_of_payment - (day_of_week % 7)) + 7;
            }
        } else if (employees[i][13].equals("2")) {
            if (day_of_week % 7 == day_of_payment % 7) {
                days_to_payment = 14;
            } else if ((day_of_week % 7) > (day_of_payment % 7)) {
                days_to_payment = (day_of_payment - (day_of_week % 7)) + 21;
            } else if ((day_of_week % 7) < (day_of_payment % 7)) {
                days_to_payment = (day_of_payment - (day_of_week % 7)) + 14;
            }
        } else if (employees[i][13].equals("1")) {
            if (month < 12) {
                if (day_of_payment == -1) {
                    days_to_payment = (calendary[month - 1][2] - day) + calendary[month][1];
                } else {
                    days_to_payment = (calendary[month - 1][2] - day) + day_of_payment;
                }
            } else {
                int aux_day_of_week = day_of_week + (31 - day) + 1;
                calculateCalendary(calendary, aux_day_of_week, year);
                if (day_of_payment == -1) {
                    days_to_payment = (31 - day) + calendary[1][1];
                } else {
                    days_to_payment = (31 - day) + day_of_payment;
                }
                calculateCalendary(calendary, aux_day_of_week - 365, year);
            }
        }

        employees[i][12] = Integer.toString(days_to_payment);
    }

    public static void createNewSchedule(int[] schedule) {
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

    public static void chooseSchedule(String[][] employees, int[] schedule, int[][] calendary, int current_employees, int day_of_week, int day, int month, int year) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o id do funcionário ou digite -1 para ver o número de todos os funcionários:");
        String option = input.nextLine();


        if (option.equals("-1")) {
            listOfEmployees(employees, current_employees);

            System.out.println("Digite o id do funcionário:");
            option = input.nextLine();
        }

        for (int i = 0; i < 50; i++) {
            if (employees[i][4] != null) {
                if (employees[i][4].equals(option)) {
                    System.out.printf("Digite a agenda que o funcionário %s irá aderir:\n", employees[i][0]);
                    System.out.printf("(1) - Mensalmente\n(2) - Bi-Semanalmente\n(3) - Semanalmente\n");
                    option = input.nextLine();
                    employees[i][13] = option;
                    int option_int = Integer.parseInt(option) - 1;
                    if (option.equals("1")) {
                        employees[i][14] = Integer.toString(schedule[option_int]);
                        updatePayment(employees, calendary, day_of_week, day, month, year, i);
                    } else if (option.equals("2")) {
                        employees[i][14] = Integer.toString(schedule[option_int]);
                        updatePayment(employees, calendary, day_of_week, day, month, year, i);
                    } else if (option.equals("3")) {
                        employees[i][14] = Integer.toString(schedule[option_int]);
                        updatePayment(employees, calendary, day_of_week, day, month, year, i);
                    }

                    break;
                }
            }
        }
    }

    public static int undoRedo(String[][][] undo_redo, String[][] employees, int actual_index, int actual_size) {
        Scanner input = new Scanner(System.in);
        System.out.printf("(1) - Undo\n(2) - Redo\n(3) - Sair\n");
        int option = input.nextInt();

        while (option != 3) {
            if (option == 1) {
                if (actual_index > 0) {
                    actual_index--;
                    System.out.println("Undo realizado com sucesso!");
                } else {
                    System.out.println("Undo não pôde ser realizado!");
                }
            } else if (option == 2) {
                if (actual_index < actual_size) {
                    actual_index++;
                    System.out.println("Redo realizado com sucesso!");
                } else {
                    System.out.println("Redo não pôde ser realizado!");
                }
            }
            System.out.printf("(1) - Undo\n(2) - Redo\n(3) - Sair\n");
            option = input.nextInt();
        }
        copy(undo_redo, employees, actual_index);
        return actual_index;
    }


    public static void payEmployee(String[][] employees, int i, double actual_salary) {
        double total;
        System.out.printf("Sálario atual: %.2f\n", actual_salary);

        if (!employees[i][10].equals("0") && actual_salary >= 0.0) {
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


    public static void payroll(String[][] employees, int[][] calendary, int current_employees, int day_of_week, int day, int month, int year) {
        for (int i = 0; i < current_employees; i++) {
            double actual_salary = Double.parseDouble(employees[i][6]);
            double taxes = 0;
            double total;
            double salary;
            int days_to_payment; // updates the quantity of days to the next payment

            if (day == 1) { // syndicate´s day of payment
                if (employees[i][7].equals("1")) {
                    salary = Double.parseDouble(employees[i][3]);
                    taxes = Double.parseDouble(employees[i][9]);
                    total = (salary * taxes) / 100.0;
                    actual_salary -= total;
                    System.out.printf("O funcionário %s, de id de sindicato %s, teve descontado %.2f de seu salário pela sua participação no sindicato!\n", employees[i][0], employees[i][8], total);
                    employees[i][6] = Double.toString(actual_salary);
                }
            }

            if (employees[i][12].equals("0")) {
                System.out.printf("Nome: %s | ID : %s\n", employees[i][0], employees[i][4]);
                if (employees[i][2].equals("2") && !employees[i][5].equals("0")) {
                    actual_salary += Double.parseDouble(employees[i][3]) / 2.0;
                    payEmployee(employees, i, actual_salary);
                } else {
                    payEmployee(employees, i, actual_salary);
                }
                updatePayment(employees, calendary, day_of_week, day, month, year, i);
                employees[i][6] = "0";
                if (employees[i][2].equals("2") && employees[i][5].equals("0")) {
                    employees[i][6] = employees[i][3];
                }

                if (employees[i][11].equals("1")) {
                    System.out.printf("Recebeu o pagamento através de um cheque para %s\n\n", employees[i][1]);
                } else if (employees[i][11].equals("2")) {
                    System.out.println("Recebeu o pagamento através de um cheque em mãos\n\n");
                } else if (employees[i][11].equals("3")) {
                    System.out.println("Recebeu o pagamento através de um depósito na conta bancária\n\n");
                }
            } else {
                System.out.printf("O funcionário %s não recebeu nenhum pagamento hoje\n", employees[i][0]);
            }
            days_to_payment = Integer.parseInt(employees[i][12]) - 1;
            employees[i][12] = Integer.toString(days_to_payment);
        }
        System.out.printf("Folha de pagamento realizada na data %d/%d/%d !\n", day, month, year);
    }

    public static void changeData(String[][] employees, int current_employees, int day_of_week, int day, int month, int year, int[][] calendary, int[] schedule) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o id do funcionário ou digite -1 para ver o número de todos os funcionários:");
        String option = input.nextLine();
        int aux = 0; // verifica se ocorreu alguma mudança no tipo de trabalho, se foi adicionada alguma comissão ou se o salário foi alterado


        if (option.equals("-1")) {
            listOfEmployees(employees, current_employees);

            System.out.println("Digite o id do funcionário:");
            option = input.nextLine();
        }

        for (int i = 0; i < 50; i++) {
            if (employees[i][4] != null) {
                if (employees[i][4].equals(option)) {
                    System.out.println("Dados antes da modificação:");
                    printar_dados(employees, i);
                    System.out.println("Digite 1 para modificar o nome e 0 para não modificar:");
                    option = input.nextLine();
                    if (option.equals("1")) {
                        System.out.println("Digite o nome:");
                        String name = input.nextLine();
                        employees[i][0] = name;
                    }

                    System.out.println("Digite 1 para modificar o endereço e 0 para não modificar:");
                    option = input.nextLine();
                    if (option.equals("1")) {
                        System.out.println("Digite o endereço:");
                        String address = input.nextLine();
                        employees[i][1] = address;
                    }

                    System.out.println("Digite 1 para modificar o tipo de trabalho e 0 para não modificar:");
                    option = input.nextLine();
                    if (option.equals("1")) {
                        System.out.println("(1) Horista\n(2) Salariado");
                        String type = input.nextLine();
                        if (!employees[i][2].equals(type)) {
                            employees[i][2] = type;
                            updateModeOfPayment(employees, schedule, i);
                            updatePayment(employees, calendary, day_of_week, day, month, year, i);
                            aux = 1;
                        }
                    }

                    System.out.println("Digite 1 para modificar a comissão e 0 para não modificar:");
                    option = input.nextLine();
                    if (option.equals("1")) {
                        System.out.println("Digite a comissão do funcionário, caso não exista digite 0:");
                        String commission = input.nextLine();
                        if ((employees[i][5].equals("0") && !commission.equals("0")) || (!employees[i][5].equals("0") && commission.equals("0"))) {
                            employees[i][5] = commission;
                            updateModeOfPayment(employees, schedule, i);
                            updatePayment(employees, calendary, day_of_week, day, month, year, i);
                            aux = 1;
                        }
                    }

                    System.out.println("Digite 1 para modificar o salário e 0 para não modificar:");
                    option = input.nextLine();
                    if (option.equals("1")) {
                        System.out.println("Digite o novo salário:");
                        String salary = input.nextLine();
                        employees[i][3] = salary;
                        aux = 1;
                    }

                    if (aux == 1) { // próximo contracheque deverá ser reiniciado
                        if ((employees[i][2].equals("1") || (employees[i][2].equals("2") && !employees[i][5].equals("0")))) {
                            employees[i][6] = "0";
                        } else if (employees[i][2].equals("2") && employees[i][5].equals("0")) {
                            employees[i][6] = employees[i][3];
                        }
                    }

                    System.out.println("Digite 1 para modificar o id e 0 para não modificar:");
                    option = input.nextLine();
                    if (option.equals("1")) {
                        System.out.println("Digite o novo id(número natural válido):");
                        String new_id = input.nextLine();
                        for (int j = 0; j < 50; j++) {
                            if (employees[j][4] != null) {
                                if (employees[j][4].equals(new_id) && j != i) {
                                    System.out.println("Digite um id válido:");
                                    new_id = input.nextLine();
                                    j = 0;
                                }
                            }
                        }
                        employees[i][4] = new_id;
                    }

                    System.out.println("Digite 1 para modificar a quantia do próximo contracheque e 0 para não modificar:");
                    option = input.nextLine();
                    if (option.equals("1")) {
                        System.out.println("Digite o valor do novo contracheque:");
                        String atual_salary = input.nextLine();
                        employees[i][6] = atual_salary;
                    }

                    System.out.println("Digite 1 para modificar a participação no sindicato e 0 para não modificar:");
                    option = input.nextLine();
                    if (option.equals("1")) {
                        System.out.println("Digite 1 para participar e 0 para não participar:");
                        String syndicate = input.nextLine();
                        employees[i][7] = syndicate;

                        if (syndicate.equals("0")) {
                            System.out.println("Como você saiu do sindicato o seu id e a sua taxa serão removidos automaticamente.");
                            employees[i][8] = "-1";
                            employees[i][9] = "0";
                        }
                    }

                    System.out.println("Digite 1 para modificar a identificação no sindicato e 0 para não modificar:");
                    option = input.nextLine();
                    if (option.equals("1")) {
                        System.out.println("Digite a identificação do sindicato(número natural válido):");
                        String id_syndicate = input.nextLine();
                        for (int j = 0; j < 50; j++) {
                            if (employees[j][8] != null) {
                                if (employees[j][8].equals(id_syndicate) && j != i) {
                                    System.out.println("Digite um id de sindicato válido(id de sindicato já existente):");
                                    id_syndicate = input.nextLine();
                                    i = 0;
                                }
                            }
                        }
                        employees[i][8] = id_syndicate;
                    }

                    System.out.println("Digite 1 para modificar a taxa do sindicato e 0 para não modificar:");
                    option = input.nextLine();
                    if (option.equals("1")) {
                        System.out.println("Digite a taxa do sindicato:");
                        String tax_syndicate = input.nextLine();
                        employees[i][9] = tax_syndicate;
                    }

                    System.out.println("Digite 1 para modificar a taxa de serviço do sindicato e 0 para não modificar:");
                    option = input.nextLine();
                    if (option.equals("1")) {
                        System.out.println("Digite a taxa de serviço do sindicato:");
                        String tax_service = input.nextLine();
                        employees[i][10] = tax_service;
                    }

                    System.out.println("Digite 1 para modificar o metódo de pagamento e 0 para não modificar:");
                    option = input.nextLine();
                    if (option.equals("1")) {
                        System.out.println("(1) Receber o pagamento em cheque pelos correios\n(2) Receber o pagamento em cheque em mãos\n(3) Receber o pagamento na conta bancária");
                        String payment = input.nextLine();
                        employees[i][11] = payment;
                    }

                    System.out.println("Alterações completas!");
                    System.out.println("Dados depois da alteração");
                    printar_dados(employees, i);
                }
            }
        }
    }

    public static void addServiceTax(String[][] employees, int current_employees) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o id do sindicato do funcionário ou digite -1 para ver o número de todos os funcionários:");
        String option = input.nextLine();

        if (option.equals("-1")) {
            listOfEmployees(employees, current_employees);

            System.out.println("Digite o id do sindicato do funcionário:");
            option = input.nextLine();
        }

        for (int i = 0; i < 50; i++) {
            if (employees[i][8] != null) {
                if (employees[i][8].equals(option)) {
                    System.out.println("Digite o nome do serviço:");
                    String service_name = input.nextLine();
                    System.out.println("Digite a taxa do serviço");
                    String service_tax = input.nextLine();
                    employees[i][10] = service_tax;

                    break;
                }
            }
        }
    }

    public static void addSale(String[][] employees, int current_employees) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o id do funcionário ou digite -1 para ver o número de todos os funcionários:");
        String option = input.nextLine();


        if (option.equals("-1")) {
            listOfEmployees(employees, current_employees);

            System.out.println("Digite o id do funcionário:");
            option = input.nextLine();
        }

        for (int i = 0; i < 50; i++) {
            if (employees[i][4].equals(option)) {
                System.out.printf("Digite o valor da venda que o funcionário %s, com o id %s, realizou hoje:\n", employees[i][0], employees[i][4]);
                String sale = input.nextLine();
                double sale_double = Double.parseDouble(sale);
                double actual_salary = Double.parseDouble(employees[i][6]);
                double commission = Double.parseDouble(employees[i][5]);
                double total = actual_salary + ((commission * sale_double) / 100);
                String total_string = Double.toString(total);
                employees[i][6] = total_string;

                break;
            }
        }
    }

    public static void addHoursWorked(String[][] employees, int current_employees) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o id do funcionário ou digite -1 para ver o número de todos os funcionários:");
        String option = input.nextLine();


        if (option.equals("-1")) {
            listOfEmployees(employees, current_employees);

            System.out.println("Digite o id do funcionário:");
            option = input.nextLine();
        }

        for (int i = 0; i < 50; i++) {
            if (employees[i][4] != null) {
                if (employees[i][4].equals(option)) {
                    System.out.printf("Digite a hora de entrada do funcionário %s:\n", employees[i][4]);
                    int entry_hour = input.nextInt();
                    System.out.printf("Digite o minuto de entrada do funcionário %s:\n", employees[i][4]);
                    int entry_minute = input.nextInt();
                    System.out.printf("Digite a hora de saída do funcionário %s:\n", employees[i][4]);
                    int exit_hour = input.nextInt();
                    System.out.printf("Digite o minuto de entrada do funcionário %s:\n", employees[i][4]);
                    int exit_minute = input.nextInt();

                    int total_hours;

                    if (exit_hour == entry_hour) {
                        total_hours = 24;
                    } else if (entry_hour > exit_hour) {
                        total_hours = 24 - entry_hour + exit_hour;
                    } else {
                        total_hours = exit_hour - entry_hour;
                    }

                    if (entry_minute > exit_minute) {
                        total_hours--;
                    }

                    if (employees[i][2].equals("1")) {
                        double actual_salary = Double.parseDouble(employees[i][6]);
                        double salary = Double.parseDouble(employees[i][3]);

                        if (total_hours > 8) {
                            actual_salary += salary * 8 + (salary * 1.5 * (total_hours - 8));
                        } else {
                            actual_salary += salary * total_hours;
                        }

                        employees[i][6] = Double.toString(actual_salary);
                    }

                    break;
                }
            }
        }
    }

    public static void remove(String[][] employees, int position, int current_employees) {
        if (position == 49) {
            employees[position][0] = null;
            employees[position][1] = null;
            employees[position][2] = null;
            employees[position][3] = null;
            employees[position][4] = null;
            employees[position][5] = null;
            employees[position][6] = null;
            employees[position][7] = null;
            employees[position][8] = null;
            employees[position][9] = null;
            employees[position][10] = null;
            employees[position][11] = null;
            employees[position][12] = null;
            employees[position][13] = null;
            employees[position][14] = null;
        } else {
            for (int i = position; i < current_employees; i++) {
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
                employees[i][13] = employees[i + 1][13];
                employees[i][14] = employees[i + 1][14];
            }
        }
    }

    public static void removeEmployee(String[][] employees, int current_employees) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o id do funcionário que deseja remover ou digite -1 para ver o número de todos os funcionários:");
        String option = input.nextLine();


        if (option.equals("-1")) {
            listOfEmployees(employees, current_employees);

            System.out.println("Digite o id do funcionário que deseja remover:");
            option = input.nextLine();
        }

        for (int i = 0; i < current_employees; i++) {
            if (employees[i][4].equals(option)) {
                remove(employees, i, current_employees);
                break;
            }
        }
    }

    public static void addEmployee(String[][] employees, int current_employees, int id, int id_syndicate, int day_of_week, int day, int month, int year, int[][] calendary, int[] schedule) {
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

        for (int i = 0; i < 50; i++) {
            if (i == current_employees) {
                employees[i][0] = name;
                employees[i][1] = address;
                employees[i][2] = type;
                employees[i][3] = salary;
                for (int j = 0; j < current_employees; j++) {
                    if (employees[j][4] != null && j != i) {
                        if (employees[j][4].equals(id_string)) {
                            id++;
                            j = 0;
                        }
                    }
                }
                id_string = Integer.toString(id);
                employees[i][4] = id_string;
                employees[i][5] = commission;
                employees[i][6] = "0";
                employees[i][7] = belongs_to_syndicate;
                employees[i][8] = "-1";
                employees[i][9] = "0";
                employees[i][10] = "0";
                employees[i][11] = payment;
                employees[i][12] = "0";

                if (employees[i][2].equals("2") && employees[i][5].equals("0")) {
                    employees[i][6] = employees[i][3];
                }

                if (employees[i][7].equals("1")) {
                    System.out.println("Digite a taxa do sindicato:");
                    String tax_syndicate = input.nextLine();
                    System.out.println();

                    for (int j = 0; j < current_employees; j++) {
                        if (employees[j][8] != null && j != i) {
                            if (employees[j][8].equals(id_syndicate_string)) {
                                id_syndicate++;
                                j = 0;
                            }
                        }
                    }
                    id_syndicate_string = Integer.toString(id_syndicate);
                    employees[i][8] = id_syndicate_string;
                    employees[i][9] = tax_syndicate;
                }

                updateModeOfPayment(employees, schedule, i);
                updatePayment(employees, calendary, day_of_week, day, month, year, i);
                break;
            }
        }
    }

    public static int bissexto(int year) {
        if(year < 100 && year % 4 == 0) {
            return 1;
        }
        if(year % 4 == 0 && year % 100 != 0) {
            return 1;
        }
        if(year % 4 == 0 && year % 400 == 0) {
            return 1;
        }

        return 0;
    }


    public static void calculateCalendary(int[][] calendary, int initial_day, int year) {
        if(bissexto(year) == 1) {
            for (int i = 0; i < 12; i++) {
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
                } else if (i == 1) {
                    calendary[i][0] = initial_day;
                    calendary[i][2] = 29;

                    if ((initial_day + 28) % 7 <= 5 && (initial_day + 28) % 7 > 0) {
                        calendary[i][1] = 29;
                    } else if ((initial_day + 27) % 7 <= 5 && (initial_day + 27) % 7 > 0) {
                        calendary[i][1] = 28;
                    } else if ((initial_day + 26) % 7 <= 5 && (initial_day + 26) % 7 > 0) {
                        calendary[i][1] = 27;
                    }
                    initial_day = (initial_day + 29) % 7;
                } else if (i == 3 || i == 5 || i == 8 || i == 10) {
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
        else {
            for (int i = 0; i < 12; i++) {
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
                } else if (i == 1) {
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
                } else if (i == 3 || i == 5 || i == 8 || i == 10) {
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
    }

    public static int updateUndoRedo(String[][] employees, String[][][] undo_redo, int[][] date, int actual_index, int initial_day, int day_of_week, int day, int month, int year) {
        if (actual_index == 50) {
            actual_index = 49;
            for (int i = 0; i < 49; i++) {
                date[i][0] = date[i + 1][0];
                date[i][1] = date[i + 1][1];
                date[i][2] = date[i + 1][2];
                date[i][3] = date[i + 1][3];
                date[i][4] = date[i + 1][4];
                for (int j = 0; j < 50; j++) {
                    undo_redo[i][j][0] = undo_redo[i + 1][j][0];
                    undo_redo[i][j][1] = undo_redo[i + 1][j][1];
                    undo_redo[i][j][2] = undo_redo[i + 1][j][2];
                    undo_redo[i][j][3] = undo_redo[i + 1][j][3];
                    undo_redo[i][j][4] = undo_redo[i + 1][j][4];
                    undo_redo[i][j][5] = undo_redo[i + 1][j][5];
                    undo_redo[i][j][6] = undo_redo[i + 1][j][6];
                    undo_redo[i][j][7] = undo_redo[i + 1][j][7];
                    undo_redo[i][j][8] = undo_redo[i + 1][j][8];
                    undo_redo[i][j][9] = undo_redo[i + 1][j][9];
                    undo_redo[i][j][10] = undo_redo[i + 1][j][10];
                    undo_redo[i][j][11] = undo_redo[i + 1][j][11];
                    undo_redo[i][j][12] = undo_redo[i + 1][j][12];
                    undo_redo[i][j][13] = undo_redo[i + 1][j][13];
                    undo_redo[i][j][14] = undo_redo[i + 1][j][14];
                }
            }
        }
        date[actual_index][0] = initial_day % 7;
        date[actual_index][1] = day_of_week % 7;
        date[actual_index][2] = day;
        date[actual_index][3] = month;
        date[actual_index][4] = year;
        for (int i = 0; i < 50; i++) {
            undo_redo[actual_index][i][0] = employees[i][0];
            undo_redo[actual_index][i][1] = employees[i][1];
            undo_redo[actual_index][i][2] = employees[i][2];
            undo_redo[actual_index][i][3] = employees[i][3];
            undo_redo[actual_index][i][4] = employees[i][4];
            undo_redo[actual_index][i][5] = employees[i][5];
            undo_redo[actual_index][i][6] = employees[i][6];
            undo_redo[actual_index][i][7] = employees[i][7];
            undo_redo[actual_index][i][8] = employees[i][8];
            undo_redo[actual_index][i][9] = employees[i][9];
            undo_redo[actual_index][i][10] = employees[i][10];
            undo_redo[actual_index][i][11] = employees[i][11];
            undo_redo[actual_index][i][12] = employees[i][12];
            undo_redo[actual_index][i][13] = employees[i][13];
            undo_redo[actual_index][i][14] = employees[i][14];
        }
        return actual_index;
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

        for(int j = 0; j < 2; j++) {
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
        String [][][] undo_redo = new String[50][50][15];
        int [][] date = new int [50][5];
        int [] schedule = new int[3];
        int [][] calendary = new int[12][3];
        int option, id = 1, id_syndicate = 1000, current_employees = 0, day = 0, day_of_week = 0, month = 0, year = 0, initial_day = 0;
        int actual_index = -1, actual_size = -1;
        initializeMatrix(employees, undo_redo);
        // inicialização da agenda de pagamento:
        schedule[0] = -1; // último dia útil do mês
        schedule[1] = 5; // bi-semanalmente pago toda sexta
        schedule[2] = 5; // semanalmente pago toda sexta
        // -----------------------------------------------

        System.out.println("Bem vindo ao sistema de Folha de Pagamento!");
        System.out.printf("Agora vamos configurar o sistema!\n\n");
        System.out.println("Digite o dia da semana do primeiro dia do ano:");
        System.out.printf("(1) - Segunda\n(2) - Terça\n(3) - Quarta\n(4) - Quinta\n(5) - Sexta\n(6) - Sábado\n(7) - Domingo\n");
        initial_day = input.nextInt();
        System.out.println("Digite o dia da semana da data atual:");
        System.out.printf("(1) - Segunda\n(2) - Terça\n(3) - Quarta\n(4) - Quinta\n(5) - Sexta\n(6) - Sábado\n(7) - Domingo\n");
        day_of_week = input.nextInt();
        System.out.println("Digite o dia do mês da data atual:");
        day = input.nextInt();
        System.out.printf("(1) - Janeiro\n(2) - Fevereiro\n(3) - Março\n(4) - Abril\n(5) - Maio\n(6) - Junho\n(7) - Julho\n(8) - Agosto\n(9) - Setembro\n(10) - Outubro\n(11) - Novembro\n(12) - Dezembro\n");
        month = input.nextInt();
        System.out.println("Digite o ano da data atual:");
        year = input.nextInt();
        calculateCalendary(calendary, initial_day, year);

        while(true) {
            System.out.println("Digite uma das opções abaixo para executar o programa:");
            System.out.printf("\n0 - Parar o programa\n1 - Adicionar um funcionário\n2 - Remover um funcionário\n3 - Lançar um cartão de ponto\n4 - Adicionar venda\n5 - Adicionar uma taxa de serviço\n6 - Alterar dados de um funcionário\n7 - Rodar o sistema de pagamentos\n8 - Undo/Redo\n9 - Modificar agenda de pagamento\n10 - Adicionar uma nova agenda de pagamento\n");
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
                if(current_employees <= 48) {
                    addEmployee(employees, current_employees, id, id_syndicate, day_of_week, day, month, year, calendary, schedule);
                    System.out.println("Funcionário adicionado com sucesso!");
                    id++;
                    id_syndicate++;
                    actual_index++;
                    actual_index = updateUndoRedo(employees, undo_redo, date, actual_index, initial_day, day_of_week, day, month, year);
                    actual_size = actual_index;
                }
                else {
                    System.out.println("Não é possível adicionar mais funcionários.");
                }
            }
            else if(option == 2) {
                removeEmployee(employees, current_employees);
                System.out.println("Funcionário removido com sucesso!");
                actual_index++;
                actual_index = updateUndoRedo(employees, undo_redo, date, actual_index, initial_day, day_of_week, day, month, year);
                actual_size = actual_index;
            }
            else if(option == 3) {
                addHoursWorked(employees, current_employees);
                System.out.println("Cartão de ponto adicionado com sucesso!");
                actual_index++;
                actual_index = updateUndoRedo(employees, undo_redo, date, actual_index, initial_day, day_of_week, day, month, year);
                actual_size = actual_index;
            }
            else if(option == 4) {
                addSale(employees, current_employees);
                System.out.println("Venda adicionada com sucesso!");
                actual_index++;
                actual_index = updateUndoRedo(employees, undo_redo, date, actual_index, initial_day, day_of_week, day, month, year);
                actual_size = actual_index;
            }
            else if(option == 5) {
                addServiceTax(employees, current_employees);
                System.out.println("Serviço adicionado com sucesso!");
                actual_index++;
                actual_index = updateUndoRedo(employees, undo_redo, date, actual_index, initial_day, day_of_week, day, month, year);
                actual_size = actual_index;
            }
            else if(option == 6) {
                changeData(employees, current_employees, day_of_week, day, month, year, calendary, schedule);
                System.out.println("Dado(s) modificado(s) com sucesso!");
                actual_index++;
                actual_index = updateUndoRedo(employees, undo_redo, date, actual_index, initial_day, day_of_week, day, month, year);
                actual_size = actual_index;
            }
            else if(option == 7) {
                payroll(employees, calendary, current_employees, day_of_week, day, month, year);
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

                if(month == 13) { //todo ano novo o pagamento é reiniciado
                    day = 1;
                    month = 1;
                    year++;
                    initial_day = day_of_week % 7;
                    calculateCalendary(calendary, day_of_week, year);
                    updateAllPayment(employees, calendary, day_of_week, day, month, year);
                }
                actual_index++;
                actual_index = updateUndoRedo(employees, undo_redo, date, actual_index, initial_day, day_of_week, day, month, year);
                actual_size = actual_index;
                System.out.printf("Estamos na data %d/%d/%d !\n", date[actual_index][2], date[actual_index][3], date[actual_index][4]);
            }
            else if(option == 8) {
                actual_index = undoRedo(undo_redo, employees, actual_index, actual_size);
                initial_day = date[actual_index][0];
                day_of_week = date[actual_index][1];
                day = date[actual_index][2];
                month = date[actual_index][3];
                if(year != date[actual_index][4]) {
                    year = date[actual_index][4];
                    calculateCalendary(calendary, initial_day, year);
                }
            }
            else if(option == 9) {
                System.out.println("Escolher uma nova agenda irá reiniciar os dias restantes para o próximo pagamento do funcionário");
                chooseSchedule(employees, schedule, calendary, current_employees, day_of_week, day, month, year);
                actual_index++;
                actual_index = updateUndoRedo(employees, undo_redo, date, actual_index, initial_day, day_of_week, day, month, year);
                actual_size = actual_index;
            }
            else if(option == 10) {
                createNewSchedule(schedule);
            }

            System.out.printf("\n-------------------------------------------------------\n");
            current_employees = updateCurrentEmployee(employees);
        }

        System.out.println("Obrigado por utilizar o nosso sistema!");
    }
}
