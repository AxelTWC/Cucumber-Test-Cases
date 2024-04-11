package seg3102team3.project.application.usecases

import java.util.UUID

interface GenerateDrugReport {
    fun generateDrugReport(agentID: UUID, drugDIN: UInt, startDate: String, endDate: String, description: String): String?
}