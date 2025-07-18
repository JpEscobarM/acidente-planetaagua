package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Comando {
	
	private final String comando;
	private final String caminho;
	private int porta;
	private Process processo;
	
	public Comando(String cmd, String dir, int porta)
	{
		this.comando = cmd;
		this.caminho = dir;
		this.porta = porta;
	}
	
	
	public static void matarProcessoNaPorta(int porta) {
        try {
         
            Process netstat = new ProcessBuilder("cmd.exe", "/c", "netstat -ano | findstr :" + porta).start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(netstat.getInputStream()));

            String linha;
            boolean achou = false;

            while ((linha = reader.readLine()) != null) {
                System.out.println("Encontrado: " + linha);

                String[] tokens = linha.trim().split("\\s+");
                if (tokens.length < 5) continue;

                String estado = tokens[3];
                String pid = tokens[4];

                if ("LISTENING".equalsIgnoreCase(estado)) {
                    achou = true;
                    System.out.println("PID na porta " + porta + ": " + pid);

                    
                    Process kill = new ProcessBuilder("cmd.exe", "/c", "taskkill /PID " + pid + " /F").start();
                    BufferedReader killReader = new BufferedReader(new InputStreamReader(kill.getInputStream()));

                    String killLinha;
                    while ((killLinha = killReader.readLine()) != null) {
                        System.out.println(killLinha);
                    }

                    kill.waitFor();
                    System.out.println("Processo " + pid + " morto com sucesso!");
                }
            }

            netstat.waitFor();

            if (!achou) {
                System.out.println("Nenhum processo escutando na porta " + porta);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void execCMD() throws IOException, InterruptedException {
		
		 ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", this.comando);
		 pb.directory(new File(this.caminho));
		 pb.redirectErrorStream(true);
	
		this.processo = pb.start();
	
		 
	        try (BufferedReader reader = new BufferedReader(
	                new InputStreamReader(processo.getInputStream()))) {
	            String linha;
	            while ((linha = reader.readLine()) != null) {
	                System.out.println(linha);
	            }
	        }
	        
	   
	        processo.waitFor();
	    
	}
	
	public void parar() {
		if (this.processo != null && this.processo.isAlive())
		{
			processo.destroy();
			
			this.matarProcessoNaPorta(this.porta);
			
			System.out.println("Processo interrompido.");
		}
	}
	
}
