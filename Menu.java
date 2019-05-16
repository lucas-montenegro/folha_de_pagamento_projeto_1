import java.util.Scanner;

public class Menu { // adicionar metodo de pagamento
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

    public static void payroll(String [][] employees, int current_employees, int day) { // adicionar um metodo de tirar a taxa do sindicato em um dia do mes para todo mundo
        for(int i = 0; i < current_employees; i++) {
            double actual_salary = Double.parseDouble(employees[i][6]);
            double taxes;
            int verify = 0; // verifies if the employee received any payment

            if(employees[i][2].equals("3") && employees[i][5].equals("0")) {
                if(day == 31) {
                    System.out.printf("Sálario atuak: %.2lf\n", actual_salary);
                    verify = 1;

                    if(employees[i][7].equals("1")) {
                        taxes = Double.parseDouble(employees[i][9]);
                        taxes = (actual_salary * taxes) / 100;
                        actual_salary -= taxes;
                        System.out.printf("-%.2lf do sindicato\n", taxes);
                    }
                    if(!employees[i][10].equals("0")) {
                        taxes = Double.parseDouble(employees[i][10]);
                        taxes = (actual_salary * actual_salary) / 100;
                        actual_salary -= taxes;
                        System.out.printf("-%.2lf de serviços\n", taxes);
                        employees[i][10] = "0";
                    }

                    employees[i][6] = Double.toString(actual_salary);
                    System.out.printf("Foi pago %s ao funcionário %s, de id %s ", employees[i][6], employees[i][0], employees[i][4]);
                    employees[i][6] = "0";
                }
            }
            else if(employees[i][2].equals("3") && !employees[i][5].equals("0")) {
                if(day == 12 || day == 26) {
                    verify = 1;
                    double partial_salary = (Double.parseDouble(employees[i][3])) / 2.0;
                    actual_salary += partial_salary;
                    System.out.printf("Sálario atuak: %.2lf\n", actual_salary);

                    if(!employees[i][10].equals("0")) {
                        taxes = Double.parseDouble(employees[i][10]);
                        taxes = (actual_salary * taxes) / 100;
                        actual_salary -= taxes;
                        System.out.printf("-%.2lf de serviços\n", taxes);
                        employees[i][10] = "0";
                    }

                    employees[i][6] = Double.toString(actual_salary);
                    System.out.printf("Foi pago %s ao funcionário %s, de id %s ", employees[i][6], employees[i][0], employees[i][4]);
                    employees[i][6] = "0";


                }
            }
            /*else if() {

            }
            else if() {

            }*/ //fazer depois

            if(verify == 1) {
                if(employees[i][11].equals("1")) {
                    System.out.printf("recebeu o pagamento através de um cheque para %s\n", employees[i][1]);
                }
                else if(employees[i][11].equals("2")) {
                    System.out.println("recebeu o pagamento através de um cheque em mãos");
                }
                else if(employees[i][11].equals("3")) {
                    System.out.println("recebeu o pagamento através de um depósito na conta bancária");
                }
            }
            else {
                System.out.printf("O funcionário %s não recebeu nenhum pagamento hoje, dia %d\n", employees[i][0], day);
            }
        }
    }

    public static void changeData(String [][] employees, int current_employees) {
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
                    System.out.println("(1) Horista\n(2) Comissionado\n(3) Salariado");
                    String type = input.nextLine();
                    employees[i][2] = type;
                }

                System.out.println("Digite 1 para modificar a comissão e 0 para não modificar");
                option = input.nextLine();
                if(option.equals("1")) {
                    System.out.println("Digite a comissão do funcionário, caso não exista digite 0:");
                    String commission = input.nextLine();
                    employees[i][5] = commission;
                }

                System.out.println("Digite 1 para modificar o metódo de pagamento e 0 para não modificar");
                option = input.nextLine();
                if(option.equals("1")) {
                    System.out.println("(1) Receber o pagamento em cheque pelos correios\n(2) Receber o pagamento em cheque em mãos\n(3) Receber o pagamento na conta bancária:");
                    String payment = input.nextLine();
                    employees[i][11] = payment;
                }

                System.out.println("Digite 1 para modificar a participação no sindicato e 0 para não modificar");
                option = input.nextLine();
                if(option.equals("1")) {
                    System.out.println("Digite 1 para participar e 0 para não participar:");
                    String syndicate = input.nextLine();
                    employees[i][7] = syndicate;
                }

                System.out.println("Digite 1 para modificar a identificação no sindicato e 0 para não modificar");
                option = input.nextLine();
                if(option.equals("1")) {
                    System.out.println("Digite a identificação do sindicato(número inteiro válido):");
                    String id_syndicate = input.nextLine();
                    employees[i][8] = id_syndicate;
                }

                System.out.println("Digite 1 para modificar a taxa do sindicato e 0 para não modificar");
                option = input.nextLine();
                if(option.equals("1")) {
                    System.out.println("Digite a taxa do sindicato:");
                    String tax_syndicate = input.nextLine();
                    employees[i][9] = tax_syndicate;
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
            employees[i][10] = employees[i + 1][11];
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

    public static void addEmployee(String [][] employees, int current_employees, int id, int id_syndicate) {
        Scanner input = new Scanner(System.in);

        System.out.println("Digite o nome do funcionário:");
        String name = input.nextLine();
        System.out.println();

        System.out.println("Digite o endereço do funcionário:");
        String address = input.nextLine();
        System.out.println();

        System.out.println("(1) Horista\n(2) Comissionado\n(3) Salariado");
        String type = input.nextLine();
        System.out.println();

        System.out.println("Digite o salário do funcionário:");
        String salary = input.nextLine();
        System.out.println();

        System.out.println("(1) Receber o pagamento em cheque pelos correios\n(2) Receber o pagamento em cheque em mãos\n(3) Receber o pagamento na conta bancária:");
        String payment = input.nextLine();
        System.out.println();

        System.out.println("Digite a comissão do funcionário, caso não exista digite 0:");
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

                break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String [][] employees = new String[50][12];
        System.out.println("Bem vindo ao sistema de Folha de Pagamento!");

        int option, id = 1, id_syndicate = 1000, current_employees = 0, day = 1;

        while(true) {
            System.out.println("Digite uma das opções abaixo para executar o programa:");
            System.out.printf("\n0 - Parar o programa\n1 - Adicionar um funcionário\n2 - Remover um funcionário\n3 - Lançar um cartão de ponto\n4 - Adicionar venda\n5 - Adicionar uma taxa de serviço\n6 - Alterar dados de um funcionário\n7 - Avançar o dia\n8 - Rodar o sistema de pagamentos\n\n");
            System.out.println("Digite uma das opções:");
            option = input.nextInt();
            System.out.println();

            while(option != 0 && option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7 && option != 8) {
                System.out.println("Digite uma opção válida:");
                option = input.nextInt();
            }

            if(option == 0) {
                break;
            }
            else if(option == 1) {
                addEmployee(employees, current_employees, id, id_syndicate);
                System.out.println("Funcionário adicionado com sucesso!");
                current_employees++;
                id++;
                id_syndicate++;
            }
            else if(option == 2) {
                removeEmployee(employees, current_employees);
                System.out.println("Funcionário removido com sucesso!");
                current_employees--;
            }
            else if(option == 3) {
                addHoursWorked(employees, current_employees);
                System.out.println("Cartão de ponto adicionado com sucesso!");
            }
            else if(option == 4) {
                addSale(employees, current_employees);
                System.out.println("Venda adicionada com sucesso!");
            }
            else if(option == 5) {
                addServiceTax(employees, current_employees);
                System.out.println("Serviço adicionado com sucesso!");
            }
            else if(option == 6) {
                changeData(employees, current_employees);
                System.out.println("Dado(s) modificado(s) com sucesso!");
            }
            else if(option == 7) {
                System.out.println("Você realmente deseja avançar o dia? Todas as atividades realizadas serão limpadas");
                System.out.println("Se deseja avancar o dia digite 1, caso contrário digite 0");
                option = input.nextInt();
                if(option == 1) {
                    day++;
                    System.out.println("Dia avançado com sucesso!");
                    //clearActivities(); limpar todas as atividades realizadas no dia
                }
                else if(option == 0) {
                    System.out.println("O dia não foi avançado!");
                }
            }
            else if(option == 8) {
                payroll(employees, current_employees, day);
                System.out.println();
            }


            System.out.printf("\n-------------------------------------------------------\n\n");
        }

        for(int i = 0; i < current_employees; i++) {
            System.out.printf("Nome: %s\n", employees[i][0]);
            System.out.printf("Tipo: %s\n", employees[i][2]);
            System.out.printf("Salário Atual: %s\n", employees[i][6]);
            System.out.printf("Sindicato: %s\n", employees[i][7]);
            System.out.printf("Taxa sindicato: %s\n", employees[i][9]);
            System.out.printf("Id sindicato: %s\n\n", employees[i][8]);
        }

        System.out.println("Obrigado por utilizar o nosso sistema!");
    }
}

/* ARRAY EMPLOYEE:
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

   Obs: Em relação ao dia, temos:
   day % 7 = 1 -> Segunda
   day % 7 = 2 -> Terça
   day % 7 = 3 -> Quarta
   day % 7 = 4 -> Quinta
   day % 7 = 5 -> Sexta
   day % 7 = 6 -> Sábado
   day % 7 = 0 -> Domingo

   Obs2: O mês tem 31 dias(para esse programa)

   Obs3: A taxa do sindicato é retirada no último dia útil do mês
 */
