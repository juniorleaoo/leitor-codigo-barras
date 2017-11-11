# Código de barras

A máquina lê o código de barras e produz um arquivo com um conjunto de entradas da seguinte forma:

```
    _  _     _  _  _  _  _
  | _| _||_||_ |_   ||_||_|
  ||_  _|  | _||_|  ||_| _|	

```

- Cada entrada possui 4 linhas, cada linha possui 27 caracteres. 
- As primeiras 3 linhas contém um número do código de barras escrito com pipes (|) e underscores (_), e a 4 linha é vazia. 
- Cada numero de código de barras deve possuir 9 dígitos, composto por numeros 0 a 9. Um arquivo comum possui cerca de 500 entradas.


## Requisitos

### 1° Validação

- Realizar a leitura dos digitos de cada numero de código de barras e valida-los, da seguinte maneira: 

```
Numero:  3  4  5  8  8  2  8  6  5
Posição: i9 i8 i7 i6 i5 i4 i3 i2 i1

(i1 + 2*i2 + 3*i3 + ... + 9*i9) % (resto) 11 = 0
```

- numero % 11 = 0 é um número válido

### 2° Arquivo de saída

Para cada arquivo de entrada, escrever um arquivo de saída com o resultado dos codigos de barras. Exemplo:

```
457508000
664371495 ERR
86110??36 ILL
```   

- Um número por linha
- Se um caracter é ilegível, substituir por um "?"
- Se um número é inválido, ou possuir um "?", colocar na frente o status do número lido.
- ERR para números inválidos. Caso não passou na validação 
- ILL para números com "?"

### 3° Aprimorar a leitura

Quando um leitor de código de barras realiza uma leitura, pode acontecer de falhar e reconher um pipe ou underscore errado. Exemplo:

```
    _  _  _  _  _  _     _ 
|_||_|| || ||_   |  |  ||_ 
  | _||_||_||_|  |  |  | _|
```

O dígito 9 poderia ser um 8, caso o leitor falhe na hora de reconhecer um pipe, o 0 poderia ser um 8, o 1 poderia ser um 7 e assim sucessivamente. 

```
Observação: Esse aprimoramente será feito somente com os números com status ERR e ILL.
```

- Adicionar ou remover APENAS UM pipe ou underscore
- Se existe um número com validação válida, esse é o correto. 
- Se existir vários números, classificar o número como AMB e adicionar os números validos na sequência.

## Exemplos



#1
Entrada:
 _  _  _  _  _  _  _  _  _ 
| || || || || || || || || |
|_||_||_||_||_||_||_||_||_|

Saída:
000000000

#2
Entrada:
    _  _     _  _  _  _  _
  | _| _||_||_ |_   ||_||_|
  ||_  _|  | _||_|  ||_| _|

Saída:
123456789

#3
Entrada:
 _  _  _  _  _  _  _  _    
| || || || || || || ||_   |
|_||_||_||_||_||_||_| _|  |

Saída:
000000051

#4
Entrada:
    _  _     _  _  _  _  _ 
  | _| _||_| _ |_   ||_||_|
  ||_  _|  | _||_|  ||_| _ 

Saída:
1234?678? ILL

#5
Entrada:
                           
|_||_||_||_||_||_||_||_||_|
  |  |  |  |  |  |  |  |  |

Saída:
444444444 ERR

#6
Entrada:
 _  _  _  _  _  _  _  _  _ 
  |  |  |  |  |  |  |  |  |
  |  |  |  |  |  |  |  |  |

Saída:
777777177

#7
Entrada:
 _  _  _  _  _  _  _  _  _ 
 _| _| _| _| _| _| _| _| _|
 _| _| _| _| _| _| _| _| _|

Saída:
333393333

#8
Entrada:
 _  _  _  _  _  _  _  _  _ 
|_ |_ |_ |_ |_ |_ |_ |_ |_ 
|_||_||_||_||_||_||_||_||_|

Saída:
666666666 AMB [666566666, 686666666]

#9
Entrada:
    _  _  _  _  _  _     _ 
|_||_|| || ||_   |  |  ||_ 
  | _||_||_||_|  |  |  | _|

Saída:
490067715 AMB [490067115, 490067719, 490867715]

#10
Entrada:
    _  _     _  _  _  _  _ 
 _| _| _||_||_ |_   ||_||_|
  ||_  _|  | _||_|  ||_| _|

Saída:
123456789
