package ifsc.joe.domain.impl;

import javax.swing.*;
import java.awt.*;

public class Cavaleiro extends Personagem implements Guerreiro, ComMontaria {
    public static final String NOME_IMAGEM = "cavaleiro";
    private boolean atacando;
    private boolean montado;

    public Cavaleiro(int posX, int posY) {
        super(posX, posY, NOME_IMAGEM);
        this.montado = true;
        this.atacando = false;
    }

    public void atacar() {
        this.atacando = !this.atacando;
    }

    public void montar() {
        this.montado = !this.montado;
    }

    @Override
    public void desenhar(Graphics g, JPanel painel) {
        // verificando qual imagem carregar
        Image icone = this.carregarImagem(NOME_IMAGEM);
        // desenhando de fato a imagem no pai
        g.drawImage(icone, this.getPosX(), this.getPosY(), painel);
    }
}
