package features.configurations

import mu.KotlinLogging
import okhttp3.OkHttpClient
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.*

object SSLConfig {

    private val logger = KotlinLogging.logger {}

    private val trustAllCerts = arrayOf<TrustManager>(
            object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            }
    )

    private val trustAllSslContext: SSLContext
    private val trustAllSslSocketFactory: SSLSocketFactory

    fun trustAllSslClient(client: OkHttpClient): OkHttpClient {
        logger.warn("Using the trustAllSslClient is highly discouraged and should not be used in Production!")
        val builder = client.newBuilder()
        builder.sslSocketFactory(trustAllSslSocketFactory, trustAllCerts[0] as X509TrustManager)
        builder.hostnameVerifier { hostname, session -> true }
        return builder.build()
    }

    init {
        try {
            trustAllSslContext = SSLContext.getInstance("SSL")
            trustAllSslContext.init(null, trustAllCerts, SecureRandom())
            trustAllSslSocketFactory = trustAllSslContext.socketFactory
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        } catch (e: KeyManagementException) {
            throw RuntimeException(e)
        }
    }
}