Funcionalidades implementadas:

Base do projeto: foi implementado um sistema de herança com Personagem como classe abstrata e Aldeao, Arqueiro e 
Cavaleiro como as filhas; além disso foram feitas duas interfaces: Guerreiro, Coletor e ComMontaria para implementar 
métodos; por fim foi adicionado a funcionalidade de criar Arqueiros e Cavaleiros;

Ataque básico: o ataque foi implementado somente para os guerreiros e apresenta uma animação de espelhar o 
personagem atacando;

Sistema de morte: a morte vem com uma animação de explosão e o contador que aumenta cada vez que algum personagem morre;

Alcance variável: o alcance foi feito usando um método de distância e foi considerado tanto o tamanho do personagem 
quanto o fato de que sua referência para o java esta no canto superior esquerdo, então foi decidido fazer uma hitbox 
circular para cada personagem;

Esquiva: a esquiva foi implementada em Personagem pois se trata de um método com mesma lógica para todos, mas valores 
diferentes de chance de esquiva;

Filtro por filtro: os filtros foram implementados para cada tipo de personagem com suporte para mais implementação de 
ações depois;

Decisões: decidimos por não usar temporizadores e ao invés usar um sistema de qualquer ação após a uma animação faz 
com que a animação suma; foi decidido também a utilização de Set<Personagem> por conta do erro 
java.util.ConcurrentModificationException que acontece quando uma coleção é modificada ao mesmo tempo que é iterada, 
a solução encontrada foi criar uma cópia durante a iteração e alterar a coleção depois;
