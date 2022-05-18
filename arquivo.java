import java.io.*;
import javax.swing.*;
public class ConversorXmlParaTXT{
    
    String ncm = null;
    float valor = 0;

    public static String getNcm() {
        return ncm;
    }
    public  void setNcm(String ncm) {
        this.ncm = ncm;
    }
    public static float getValor() {
        return valor;
    }
    public static  void setValor(float valor) {
        this.valor = valor;
    }

public static void main (String[] args) {
    java.io.File diretorio = new java.io.File(JOptionPane.showInputDialog("Digite o Diretório completo"));
    java.io.File arquivo = new java.io.File(diretorio,"resultado.txt");
    java.io.File arquivoXml = new java.io.File(diretorio, "xml.xml");
    String linha = new String("vazia");
    arquivo.createNewFile();
    while (linha !=null) {
        
        linha = ler(arquivoXml);
        if (linha.contains("<dadosMercadoriaCodigoNcm>")) {
            setNcm(linha.substring(26, 34));
        } else if (linha.contains("<pisCofinsBaseCalculoValor>")){
                    setValor(Float.parseFloat(linha.substring(28, 42))/100);
                    escrever(arquivo);
                }
    }
    FileReader fileReader = new FileReader(arquivoXml);
    fileReader.close();
}

public static void escrever (File arq){
    FileWriter fileWriter= new FileWriter(arq,true);
    PrintWriter printWriter = new PrintWriter(fileWriter);
    printWriter.print(ConversorXmlParaTXT.getNcm());
    printWriter.print(" - ");
    printWriter.println(ConversorXmlParaTXT.getValor());
    printWriter.flush();
    printWriter.close();
}

public static String ler (File arq){
    FileReader fileReader = new FileReader(arq);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    String linha= bufferedReader.readLine();
    return linha;
}


}