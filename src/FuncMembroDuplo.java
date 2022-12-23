import java.io.*;

import java.util.ArrayList;

import myinputs.Ler;

public class FuncMembroDuplo {
	
	/* ------------------- CRIAR MEMBRO ---------------------------------------- *
	 * ------------------------------------------------------------------------- */
	public static void criarMembro(ArrayList<Ginasio> ginasio, int num) {
		System.out.println("Insira o primeiro nome: ");
		String p_nome = Ler.umaString();
		
		System.out.println("Insira o último nome: ");
		String u_nome = Ler.umaString();
		
		System.out.println("Insira o NIF: ");
		int nif = Ler.umInt();
		
		for(int i = 0; i < ginasio.get(num).getMembros().size(); i++) {
			if(ginasio.get(num).getMembros().get(i).getNif() == nif) {
				System.out.println("O NIF [" + nif + "] já está registado no sistema!");
				return;
			}
		}
		
		System.out.println("Insira a sua data de nascimento ");
		System.out.println("Dia: ");
		int dia = Ler.umInt();
		System.out.println("Mês (1-12): ");
		int mes = Ler.umInt();
		System.out.println("Ano: ");
		int ano = Ler.umInt();
		
		System.out.println("Insira a altura (em centímetros): ");
		int altura = Ler.umInt();
		System.out.println("Insira o peso (em quilogramas): ");
		double peso = Ler.umDouble();
		
		Pessoa pax = new Pessoa(p_nome, u_nome, nif, dia, mes, ano, altura, peso);
		Membro mem = new Membro(pax);
		
		ginasio.get(num-1).addMembros(mem); // função add membros
		ginasio.get(num-1).addTotalpax(pax);
		
		
		// ESCRITA PARA O FICHEIRO DOS MEMBROS
		/*try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("/Users/rdsilva/Developer/java/GinásioProject/membros.md"));
			
			//escrever o objeto membros no ficheiro membros.md
			os.writeInt(Membro.getUltimo());
			os.writeObject(ginasio.get(num).get...);
			os.flush();
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		// ESCRITA PARA O FICHEIRO DAS PESSOAS
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("/Users/rdsilva/Developer/java/GinásioProject/pessoas.md"));
			
			// escrever o objeto pessos no ficheiro pessoas.md
			os.writeObject(pessoas);
			os.flush();
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		} */
		
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("/Users/rdsilva/Developer/java/GinásioProject/gymdata.md"));
			
			//escrever o objeto membros no ficheiro gymdata.md
			os.writeInt(Membro.getUltimo());
			os.writeObject(ginasio.get(num));
			os.flush();
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	/* ------------------- LISTAR MEMBROS -------------------------------------- *
	 * ------------------------------------------------------------------------- */
	
	public static void listarMembro(ArrayList<Ginasio> ginasio, int num) {
		String s = "";
		for(int i = 0; i < ginasio.get(num).getMembros().size(); i++) {
			s += "\n nº [" + ginasio.get(num).getMembros().get(i).getNum_membro() + "] :: Nome: " + ginasio.get(num).getMembros().get(i).getP_nome() + " " + ginasio.get(num).getMembros().get(i).getU_nome() + ":: NIF [" + ginasio.get(num).getMembros().get(i).getNif() + "]";
		}
		System.out.println(s);
	}
	
	/* ------------------- CONSULTAR MEMBRO -------------------------------------- *
	 * --------------------------------------------------------------------------- */
	public static void consultarMembro(ArrayList<Membro> membros) {
		int escolha = 1;
		do {
			System.out.println("Deseja consultar dado:\n1 - Nome\n2 - Número de Membro\n3 - NIF\n4 - Sair");
			escolha = Ler.umInt();
			switch(escolha) {
			
			// dado o nome
			case 1: System.out.println("Insira o primeiro nome: ");
					String totalnome = "";
					String p_nome = Ler.umaString();
					for(int i = 0; i < membros.size(); i++) {
						if(membros.get(i).getP_nome().equals(p_nome)) {
							System.out.println("Insira o último nome: ");
							String u_nome = Ler.umaString();
							if(membros.get(i).getU_nome().equals(u_nome)) {
								totalnome += membros.get(i).toString() + "\n";
							}
						}
					}
					
					if(totalnome.equals("")) {
						System.out.println("Não existe nenhum membro registado com esse nome!");
					}
					else {
						System.out.println(totalnome);
					}
			break;
			
			// dado o número de membro
			case 2: System.out.println("Insira o número de membro: ");
					String totalnum = "";
					int num = Ler.umInt();
					
					for(int j = 0; j < membros.size(); j++) {
						if(num == membros.get(j).getNum_membro()) {
							totalnum = membros.get(j).toString();
						}
					}
					
					if(totalnum.equals("")) {
						System.out.println("Não existe nenhum membro registado com esse número!");
					}
					else {
						System.out.println(totalnum);
					}
			break;
			
			// dado o nif
			case 3: System.out.println("Insira o NIF: ");
					String totalnif = "";
					int nif = Ler.umInt();
					
					for(int k = 0; k < membros.size(); k++) {
						if(nif == membros.get(k).getNif()) {
							totalnif = membros.get(k).toString();
						}
					}
					
					if(totalnif.equals("")) {
						System.out.println("Não existe nenhum membro registado com esse NIF!");
					}
					else {
						System.out.println(totalnif);
					}
			break;
			}
		} while(escolha != 4);
	}
	
	/* ------------------- ALTERAR MEMBRO -------------------------------------- *
	 * ------------------------------------------------------------------------- */
	public static void alterarMembro(ArrayList<Membro> membros, ArrayList<Pessoa> pessoas) {
		System.out.println("Insira o número do membro que pretende alterar");
		int num = Ler.umInt();
		
		for(int i = 0; i < membros.size(); i++) {
			if(membros.get(i).getNum_membro() == num) {
				int escolha = 1;
				do {
					System.out.println("O que pretende alterar:\n1 - Nome\n2 - NIF\n3 - Data de Nascimento\n4 - Altura\n5 - Peso\n6 - Sair");
					escolha = Ler.umInt();
					switch(escolha) {
					
					// --------------- mudar o nome ------------------
					case 1: int escnome = 1;
							do {
								System.out.println("Deseja alterar:\n1 - Primeiro Nome\n2 - Último Nome\n3 - Ambos\n4 - Sair");
								escnome = Ler.umInt();
								switch(escnome) {
								case 1:	System.out.println("Insira o novo Primeiro nome: ");
										String p_nome = Ler.umaString();
										
										membros.get(i).setP_nome(p_nome); // set Primeiro nome
								break;
								
								case 2: System.out.println("Insira o novo Último nome: ");
										String u_nome = Ler.umaString();
										
										membros.get(i).setU_nome(u_nome); // set Último nome
								break;
								
								case 3: System.out.println("Insira o novo Primeiro nome: ");
										String p_nome2 = Ler.umaString();
										System.out.println("Insira o novo Último nome: ");
										String u_nome2 = Ler.umaString();
										
										membros.get(i).setP_nome(p_nome2); // set Primeiro nome
										membros.get(i).setU_nome(u_nome2); // set Último nome
								}
								
							} while(escnome != 4);
					break;
					
					// --------------- mudar o nif ------------------
					case 2:	int escnif = 1;
							while(escnif == 1) {
								System.out.println("Insira o novo NIF: ");
								int nif = Ler.umInt();
							
								for(int l = 0; l < membros.size(); l++) {
									if(nif == membros.get(l).getNif()) {
										System.out.println("Já existe um NIF igual registado!");
										escnif = 0;
									}
								}
								
								membros.get(i).setNif(nif); // set do novo nif
			
							}
							
							
					break;
					
					// --------------- mudar a idade ------------------
					case 3:	int escidade = 1;
							do {
								System.out.println("Deseja alterar:\n1 - Dia\n2 - Mês\n3 - Ano\n4 - Sair");
								escidade = Ler.umInt();
								switch(escidade) {
								case 1:	System.out.println("Insira o novo dia: ");
										int dia = Ler.umInt();
										
										membros.get(i).setDataNasc(membros.get(i).getDataNasc().getYear(), membros.get(i).getDataNasc().getMonthValue(), dia);
								break;
						
								case 2: System.out.println("Insira o novo mês (1-12): ");
										int mes = Ler.umInt();
								
										membros.get(i).setDataNasc(membros.get(i).getDataNasc().getYear(), mes, membros.get(i).getDataNasc().getDayOfMonth());
								break;
						
								case 3:	System.out.println("Insira o novo mês (1-12): ");
										int ano = Ler.umInt();
								
										membros.get(i).setDataNasc(ano, membros.get(i).getDataNasc().getMonthValue(), membros.get(i).getDataNasc().getDayOfMonth());
								break;
								
								}
							} while(escidade!= 4);
					break;
					}
					
				} while(escolha != 6);
			}
		}
		
		// ESCRITA PARA O FICHEIRO DOS MEMBROS
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("/Users/rdsilva/Developer/java/GinásioProject/membros.md"));
					
			//escrever o objeto membros no ficheiro membros.md
			os.writeInt(Membro.getUltimo());
			os.writeObject(membros);
			os.flush();
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
				
		// ESCRITA PARA O FICHEIRO DAS PESSOAS
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("/Users/rdsilva/Developer/java/GinásioProject/pessoas.md"));
					
			// escrever o objeto pessos no ficheiro pessoas.md
			os.writeObject(pessoas);
			os.flush();
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
}