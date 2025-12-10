package ifsc.joe.domain.impl;

import javax.swing.*;
import java.awt.*;

public class Arqueiro extends Personagem implements Guerreiro, Coletador {
    public static final String NOME_IMAGEM;
    public static final int VIDA_MAXIMA;

    private boolean atacando;
    private boolean coletando;

    static {
        NOME_IMAGEM = "arqueiro";
        VIDA_MAXIMA = 15;
    }

    public Arqueiro(int posX, int posY) {
        super(posX, posY, NOME_IMAGEM);
        this.atacando = false;
        vida = 15;
    }

    public void atacar() {
        this.atacando = !this.atacando;
    }

    public void coletar() {
        this.coletando = !this.coletando;
    }

    @Override
    public void desenhar(Graphics g, JPanel painel) {
        // verificando qual imagem carregar
        Image icone = this.carregarImagem(NOME_IMAGEM);
        // desenhando de fato a imagem no pai
        g.drawImage(icone, this.getPosX(), this.getPosY(), painel);
        //Cria a barra de vida do personagem
        criarBarraVida(g, vida, VIDA_MAXIMA);
    }
}