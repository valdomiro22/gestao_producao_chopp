package com.santos.valdomiro.gestaoproducaochopp.domain.exceptions

class AcessoNegadoException(cause: Throwable? = null) : Exception("Sem permissão de acesso", cause)
class NaoEncontradoException(cause: Throwable? = null) : Exception("Registro não encontrado", cause)
class ServicoIndisponivelException(cause: Throwable? = null) : Exception("Serviço/Internet indisponível", cause)
class ErroBancoDadosDesconhecidoException(cause: Throwable? = null) : Exception("Erro desconhecido no banco", cause)