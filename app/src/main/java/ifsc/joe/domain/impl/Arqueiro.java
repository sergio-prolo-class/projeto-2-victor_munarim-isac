package ifsc.joe.domain.impl;

import javax.swing.*;
import java.awt.*;

public class Arqueiro extends Personagem implements Guerreiro, Coletador {
    public static final String NOME_IMAGEM;
    public static final int VIDA_MAXIMA;
    public static final int ATAQUE;
    public static int mortes;

    private boolean atacando;
    private boolean coletando;

    static {
        NOME_IMAGEM = "arqueiro";
        VIDA_MAXIMA = 15;
        ATAQUE = 2;
        mortes = 0;
    }

    public Arqueiro(int posX, int posY) {
        super(posX, posY, NOME_IMAGEM, VIDA_MAXIMA);
        this.atacando = false;
        velocidade = VELOCIDADE_PADRAO;
    }

    public void atacar() {
        this.atacando = !this.atacando;
    }

    public void coletar() {
        this.coletando = !this.coletando;
    }

    public static String getMortes() {
        return String.valueOf(mortes);
    }

    @Override
    public void desenhar(Graphics g, JPanel painel) {
        // verificando qual imagem carregar
        Image icone = this.carregarImagem(NOME_IMAGEM + (this.atacando ? "2" : ""));
        // desenhando de fato a imagem no pai
        g.drawImage(icone, this.getPosX(), this.getPosY(), painel);

        if (this.getMorreu()) {
            this.desenharMorte(g, painel, this.getPosX(), this.getPosY());
            mortes++;
        }

        //Cria a barra de vida do personagem
        criarBarraVida(g, vida, VIDA_MAXIMA);
    }
}