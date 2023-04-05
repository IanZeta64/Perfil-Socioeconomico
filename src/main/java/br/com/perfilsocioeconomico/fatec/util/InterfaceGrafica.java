package br.com.perfilsocioeconomico.fatec.util;

import br.com.perfilsocioeconomico.fatec.model.Estatisticas;
import org.jfree.chart.plot.XYPlot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;


public class InterfaceGrafica extends JFrame implements ActionListener {
    private final JLabel label;
    private final JComboBox<String> comboBox;
    private final JButton chartButton;


private final Map<String, Estatisticas> estatisticasMap;

    public InterfaceGrafica(Map<String, Estatisticas> estatisticasMap) {
        super("Interface Gráfica - Perfil Socioeconomico");
        this.estatisticasMap = estatisticasMap;


        String[] QUESTIONARIO_PERGUNTAS = {"Qual o seu curso?", "Qual o período que você cursa?",
                "Qual estado do Brasil você nasceu", "Qual sua cidade de residência?", "Qual o seu gênero?",
                "Qual sua Orientação Sexual.", "Qual sua Cor Raça/Etnia ?", "Qual a sua data de nascimento?",
                "Qual o seu estado civil?", "Você é portador de alguma deficiência?", "Nos diga qual o seu tipo de deficiência",
                "Quantos filhos você tem?", "Quantas pessoas, incluindo você, moram no seu domicílio?", "Com quem você mora atualmente?",
                "Qual a situação do domicílio onde mora?", "Tempo que reside neste domicilio?", "Qual a faixa de renda mensal da sua família (em Salários Mínimos)?",
                "Televisão / Smart TV", "Vídeo Game ultima ou penúltima geração", "Micro system ou Home Theather", "Automóvel",
                "Motocicleta", "Maquina de lavar roupa e(ou) Tanquinho", "Geladeira", "Smartphone", "Computador Desktop", "Notebook",
                "Banheiros / suites", "Telefone fixo", "Internet", "TV por assinatura (Sky,NET Claro,vivo)", "Empregada mensalista",
                "Serviço de streaming (Netflix, HBO, Disney+, etc)", "Você trabalha?", "Qual o seu vínculo com o emprego?",
                "Você trabalha na área do curso?", "Qual seu horário de trabalho?", "Qual a empresa que você está contratado agora?",
                "Você tem plano de saúde privado?", "Qual a escolaridade de sua mãe?", "Qual a escolaridade de seu pai?", "Na sua vida escolar você estudou",
                "Com que frequência você utiliza computadores?", "Em casa", "No trabalho", "Na escola", "Em outros lugares",
                "Para trabalhos profissionais", "Para trabalhos escolares", "Para entretenimento (músicas, vídeos, redes sociais, etc)",
                "Para comunicação por e-mail", "Para operações bancárias", "Para compras eletrônicas", "Como você classifica seu conhecimento em informática?",
                "Windows:","Linux:", "Mac(OS)", "Editores de textos (Word, Writer, etc.):", "Planilhas eletrônicas (Excel, Calc, etc.):",
                "Sistemas de Gestão Empresarial:", "Inglês", "Espanhol", "Outro idiomas", "Televisão:", "Internet:", "Revistas:",
                "Jornais:", "Rádio:", "Redes sociais:", "Parentes/Amigos:", "Você pesquisa e lê sobre Noticias, com qual frequência?",
                "Quais assuntos você pesquisa?", "Não considerando os livros escolares, quantos livros você lê por ano (em média)?",
                "Você lê livros literários? Se sim, qual seu gênero preferido.", "Você dedica parte do seu tempo para atividades voluntárias",
                "Qual religião você professa?*", "Você consome fontes de entretenimento?", "Quais fontes de entretenimento cultural você usa",
                "Como conheceu a FATEC Franca?", "Você já possui alguma Graduação?", "Você já possui alguma Pós-graduação?",
                "Porque você escolheu este curso?", "Qual sua maior expectativa quanto ao curso?", "Qual sua expectativa após se formar?",
                "Você já estudou nesta escola?", "Você já fez algum curso técnico?", "Qual o meio de transporte você usa para vir à escola?",
                "Escreva algumas linhas sobre sua história e seus sonhos de vida."};
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(500, 500);
        setLayout(new FlowLayout());

        label = new JLabel("Selecione a pergunta:");
        comboBox = new JComboBox<>(QUESTIONARIO_PERGUNTAS);;
        chartButton = new JButton("Enviar");

        chartButton.addActionListener(this);

        add(label);
        add(comboBox);
        add(chartButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chartButton) {
            String input = String.valueOf(comboBox.getSelectedItem());
            if (input.equals("Escreva algumas linhas sobre sua história e seus sonhos de vida."))
                new Bubble(estatisticasMap.get(input));
            else {
                new Grafico(estatisticasMap.get(input));
            }
        }
    }


}