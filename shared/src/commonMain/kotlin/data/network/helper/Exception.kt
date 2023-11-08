package data.network.helper

enum class NetworkError {
    ServiceUnavailable,
    ClientError,
    ServerError,
    UnknownError
}

class ExceptionClass(error: NetworkError): Exception(
    "Something goes wrong: $error"
)