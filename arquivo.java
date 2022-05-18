import java.io.*;
import javax.swing.*;
public class Extrator {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		java.io.File diretorio = new java.io.File(JOptionPane.showInputDialog("Digite o Diretório completo"));
		java.io.File arquivo = new java.io.File(diretorio,"resultado.txt");
		java.io.File arquivoXml = new java.io.File(diretorio, "xml.xml");
		String linha = " ";
		Arquivo lArquivo = new Arquivo();
    
		try {
			arquivo.createNewFile();
			while (linha !=null) {
		        
				linha = ler(arquivoXml);
				if (linha.contains("<dadosMercadoriaCodigoNcm>")) {
					lArquivo.setNcm(linha.substring(26, 34));
				} else if (linha.contains("<pisCofinsBaseCalculoValor>")){
	                    	lArquivo.setValor(Float.parseFloat(linha.substring(28, 42))/100);
	                    	escrever(arquivo, lArquivo);
	                	}
			}
			FileReader fileReader = new FileReader(arquivoXml);
			fileReader.close();
		}
		catch (IOException e) {
			System.out.println("erro"+e.toString());	
		}
    
    	
		
	}

	public static void escrever (File arq, Arquivo lArquivo){
		try {			
			FileWriter fileWriter= new FileWriter(arq,true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.print(lArquivo.getNcm());
			printWriter.print(" - ");
			printWriter.println(lArquivo.getValor());
			printWriter.flush();
			printWriter.close();
			} catch (IOException e) {
				System.out.println("erro"+e.toString());
				}	
	}

	public static String ler (File arq){
		String linha= "";
		try {			
			FileReader fileReader = new FileReader(arq);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
		    linha= bufferedReader.readLine();
		    bufferedReader.close();
		} catch (IOException e) {
			System.out.println("erro"+e.toString());
		}
		return linha;
	}
}

class Arquivo{

	String ncm = null;
	float valor = 0;

	public static void main(String[] args) {
			
	}
	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

}
