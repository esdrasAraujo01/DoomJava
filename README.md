
# DoomJava

## 🕹️ Descrição

**DoomJava** é uma simples engine 3D em primeira pessoa construída com Java puro, utilizando técnicas clássicas de *raycasting* — semelhante ao motor gráfico usado no jogo original *Wolfenstein 3D*. O projeto demonstra como criar um ambiente 3D navegável em tempo real sem o uso de bibliotecas externas de renderização 3D, como OpenGL.

---

## 🚀 Funcionalidades

- Renderização 3D baseada em raycasting.
- Movimento com as teclas `W`, `A`, `S`, `D`.
- Leitura de mapas baseados em arquivos `.txt`.
- Estrutura modular com separação entre lógica de jogo, renderização e carregamento de mapas.

---

## 🧠 Estrutura do Código

```
DoomJava/
│
├── src/
│   ├── game/                 # GameLoop e lógica principal
│   │   └── GameLoop.java
│   ├── graphics/             # Renderização 3D com raycasting
│   │   └── RaycastingRenderer.java
│   └── maps/                 # Leitor de mapas
│       ├── MapLoader.java
│       └── Map1.txt
│
├── out/                      # Arquivos compilados
├── .idea/                    # Configurações do projeto IntelliJ
└── DoomJava.iml              # Arquivo do projeto IntelliJ
```

---

## 📄 Formato do Mapa

O mapa é definido em um arquivo `.txt` onde:

- `#` representa uma **parede**
- `.` representa um **espaço vazio**

Exemplo (`Map1.txt`):

```
########
#......#
#..##..#
#......#
########
```

---

## 🏁 Como Executar

1. Abra o projeto com o [IntelliJ IDEA](https://www.jetbrains.com/idea/) ou outro IDE com suporte a Java.
2. Compile os arquivos na pasta `src/`.
3. Execute a classe `game.GameLoop`.

Ou, via terminal:

```bash
javac -d out src/maps/MapLoader.java src/graphics/RaycastingRenderer.java src/game/GameLoop.java
java -cp out game.GameLoop
```

---

## 🔧 Requisitos

- Java JDK 8 ou superior
- IDE Java (recomendado: IntelliJ)

---

## 💡 Objetivos Educacionais

Este projeto é ideal para:

- Estudantes aprendendo sobre gráficos 3D.
- Desenvolvedores interessados em engines simples.
- Estudos sobre simulação 3D com matemática de vetores e álgebra linear.

---

## 🧑‍💻 Autor

Desenvolvido por Esdras de Araujo.  
Inspirado pelo projeto do youtuber https://www.youtube.com/@GrandeFiasco.
