package com.example.gestionefile;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class GestioneFile {

    public String leggiFile(String fileName, Context c) {

        String fileOut;
        StringBuilder sb = new StringBuilder(); //istanzio oggetto della classe stringBuilder

        try{ //racchiudo tutto in un blocco try poichè le operazioni con i file resttuiscpno delle eccezioni quali file not found e i/o
            BufferedReader fileIn= new BufferedReader(new InputStreamReader(c.openFileInput(fileName))); //questa riga di codixce permette di istanziare un oggetto buffered reader che al suo interno avrà memorizzato il contenmuto del file in byte
            while((fileOut = fileIn.readLine()) != null) {  //ciclo while che scorre il file riga per riga ed assegma il valore della riga a fileOut, il ciclo continua fino a che non si arriva alla fine del file quando fileOut sarà null
                sb.append(fileOut + "\n");  //Ogni riga letta dal file viene "appesa" assieme al carattere /n che permette di andare a capo nell'oggetto sb così, in questo modo sb avrà lo stesso contemuto del file
            }
        }
        catch(FileNotFoundException e){  //blocco catch per la file not found exception
            Log.e("mainActivity", "Il file non esiste");
        }
        catch (IOException e) { //blocco catch per la i/o exception
            e.printStackTrace();
        }
        return sb.toString();  //il metodo restituisce l'oggetto sb con all'interno il contenuto del file
    }

    public String scriviFile(String fileName, Context c) {

        String esito = "";  //poichè questo metodo crea o scrive (in caso esista già) un file non ha un responso grafico sul successo dell'operazione ed è quindi necessario restituire una stringa che contenga dei messaggi riguardanti il successo o fallimento dell'operazuone di scrittura
        FileOutputStream fileO;
        String str = "Questo è il testo che scriveremo nel file";

        try {
            fileO = c.openFileOutput(fileName, Context.MODE_PRIVATE);  //apro il file all'interno di fileO
            fileO.write(str.getBytes()); //scrivo nel file il contenuto della stringa str convertendolo però in bytes
            fileO.close(); //chiudo il file altrimenti potrei incappare in errori
            esito = "File letto correttamente";  //memorizzo nella stringa esito un messaggio di successo
        }

        catch (FileNotFoundException e) {
            Log.e("MainActivity", "il file non esiste");
            e.printStackTrace();
            esito = "Il file a cui si vuole accedere non esiste"; //memorizzo nella stringa esito il messaggio del fallimento e la motivazione
        }

        catch (IOException e) {
            Log.e("MainActivity", "errore di I/O");
            e.printStackTrace();
            esito = "Impossibile gestire le operazioni di input o output"; //memorizzo nella stringa esito il messaggio del fallimento e la motivazione
        }
        return esito;
    }
}