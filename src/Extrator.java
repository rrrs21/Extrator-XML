import java.io.*;
import javax.swing.*;

public class Extrator {

    public static void main(String[] args) {
        java.io.File diretorio = new java.io.File(JOptionPane.showInputDialog("Digite o Diretório completo"));
        java.io.File arquivo = new java.io.File(diretorio,"resultado.csv");
        java.io.File arquivoXml = new java.io.File(diretorio, "xml.xml");
        int max=0;
        try {
            arquivo.createNewFile();
            max = ler(arquivoXml,arquivo);
            JOptionPane.showMessageDialog(null,"Extração concluída com sucesso. Quantidade de adições: "+max);
        }
        catch (IOException e) {
            System.out.println("erro"+e.toString());
        }
    }

    public static void escrever (File arq, Arquivo lArquivos){
        try {
            FileWriter fileWriter= new FileWriter(arq,true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(lArquivos.getNcm());
            printWriter.print(";");
            printWriter.printf("%4.2f\n", lArquivos.getValor());
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            System.out.println("erro"+e.toString());
        }
    }

    public static int ler (File arq, File rFile){
        String linha= "";
        int i=0;
        try {
            FileReader fileReader = new FileReader(arq);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            linha= bufferedReader.readLine();
            Arquivo auxiliArquivo = new Arquivo("0",0);
            while (linha !=null) {
                if (linha.contains("<dadosMercadoriaCodigoNcm>")) {
                    auxiliArquivo.setNcm(linha.substring(38, 46));
                } else if (linha.contains("<pisCofinsBaseCalculoValor>")){
                    auxiliArquivo.setValor(Float.parseFloat(linha.substring(39, 54))/100);
                    escrever(rFile, auxiliArquivo);
                    i++;
                }
                linha= bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("erro"+e.toString());
        }
        return i;
    }
}

class Arquivo{

    public String ncm = null;
    public float valor = 0;

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

    public Arquivo(String ncm, float valor) {
        this.ncm = ncm;
        this.valor = valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
    public Arquivo() {
    }

}


