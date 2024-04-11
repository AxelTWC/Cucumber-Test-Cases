package seg3102team3.project.contracts.testStubs.services

import seg3102team3.project.application.services.HashService
import java.math.BigInteger
import java.security.MessageDigest

class HashServiceImpl : HashService {
    override fun md5(str: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(str.toByteArray())).toString(16).padStart(32, '0')
    }
}