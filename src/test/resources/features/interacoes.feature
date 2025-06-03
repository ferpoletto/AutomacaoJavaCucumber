
Funcionalidade: Interações com elementos da aplicação web

  Cenário: Realizar todas as ações possíveis com elementos web
    Dado que abro a aplicação
    Quando clico no elemento com id "btnEnviar"
    E altero o valor do campo com id "inputNome" para "João"
    E preencho o campo com id "inputEmail" com "joao@email.com"
    E seleciono a opção "Opção 1" do dropdown com id "seletorOpcoes"
    E marco o botão de rádio com id "radioMasculino"
    E marco a caixa de seleção com id "aceitarTermos"
    E navego para a URL "/pagina-secundaria"
    Então valido que o elemento com id "mensagemSucesso" existe
    E espero 5 segundos
