package br.com.perfilsocioeconomico.fatec.util;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class ManipuladorDeArquivo {

    public void excelToCSV() {
        try {
            // Lê o arquivo Excel
            Workbook workbook = WorkbookFactory.create(Files.newInputStream(Paths.get("C:\\Users\\Ian Paschoal\\Desktop\\trabalho Roland\\perfilsocieconomico-leitura.xlsx")));

            // Seleciona a primeira planilha
            Sheet sheet = workbook.getSheetAt(0);

            List<String> lines = new ArrayList<>();

            // Itera pelas linhas da planilha
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                if (row != null) {
                    StringBuilder line = new StringBuilder();

                    // Itera pelas células da linha
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        String cellValue = "";

                        // Obtem o valor da célula
                        switch (cell.getCellType()) {
                            case STRING:
                                cellValue = cell.getStringCellValue().trim();
                                break;
                            case NUMERIC:
                                cellValue = String.valueOf(cell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                cellValue = String.valueOf(cell.getBooleanCellValue());
                                break;
                            case FORMULA:
                                cellValue = String.valueOf(cell.getCellFormula());
                                break;
                            default:
                                cellValue = "Nenhuma resposta";
                        }

                        // Adiciona o valor da célula na linha, separado por vírgula
                        if (line.length() > 0) {
                            line.append("|");
                        }

                        line.append(cellValue);
                    }

                    // Adiciona a linha na lista de linhas
                    lines.add(line.toString());
                }
            }


            // Escreve as linhas no arquivo CSV
            Files.write(Paths.get("C:\\Users\\Ian Paschoal\\Desktop\\trabalho Roland\\perfilsocieconomico-csv.csv"), lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   public List<List<String>> lerArquivo() {
       Path path = Path.of("C:\\Users\\Ian Paschoal\\Desktop\\trabalho Roland\\perfilsocieconomico-csv.csv");
       List<String> listaLinhasQuestionario;
       try {
           listaLinhasQuestionario = Files.readAllLines(path);
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
       return listaLinhasQuestionario.stream().map(linhaResposta -> {
           String[] linhaSplit = linhaResposta.split("\\|");
           return Arrays.stream(linhaSplit).toList();
       }).toList();
   }




//           Map<String, List<String>> mapChavePerguntaValorListaDeRespostas = new LinkedHashMap<>();
//           for (int i = 0; i < listaComListaDeRespostas.size(); i++) {
//               String chave = listaComListaDeRespostas.get(0).get(i);
//               List<String> listaRespostas = new ArrayList<>();
//               for (List<String> listaResposta : listaComListaDeRespostas) {
//                   listaRespostas.add(listaResposta.get(i));
//               }
//               mapChavePerguntaValorListaDeRespostas.put(chave, listaRespostas.stream().skip(1).toList());
//               listaRespostas.clear();
//           }
//          mapChavePerguntaValorListaDeRespostas.entrySet().forEach(System.out::println);


//       } catch (IOException e) {
//           throw new RuntimeException(e);
//       }
//   }
}
