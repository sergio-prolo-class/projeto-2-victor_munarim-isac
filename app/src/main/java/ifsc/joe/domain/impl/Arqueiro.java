package ifsc.joe.domain.impl;

import javax.swing.*;
import java.awt.*;

public class Arqueiro extends Personagem implements Guerreiro, Coletador {
    public static final String NOME_IMAGEM = "arqueiro";
    private boolean atacando;
    private boolean coletando;

    public Arqueiro(int posX, int posY) {
        super(posX, posY, NOME_IMAGEM);
        this.atacando = false;
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
    }
}