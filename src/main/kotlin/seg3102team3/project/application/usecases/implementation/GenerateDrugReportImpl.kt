package seg3102team3.project.application.usecases.implementation

import seg3102team3.project.application.usecases.GenerateDrugReport
import seg3102team3.project.domain.agent.facade.AgentFacade
import seg3102team3.project.domain.drug.facade.DrugFacade
import java.util.*
import java.util.ArrayList
import java.util.List
import java.util.stream.Collectors
import java.io.FileOutputStream
import com.itextpdf.text.Document
import com.itextpdf.text.Font
import com.itextpdf.text.FontFactory
import com.itextpdf.text.BaseColor
import com.itextpdf.text.Chunk;
import com.itextpdf.text.pdf.PdfWriter;



class GenerateDrugReportImpl(
    private var agentFacade: AgentFacade, private var drugFacade: DrugFacade): GenerateDrugReport {
        
        override fun generateDrugReport(agentID: UUID, drugDIN: UInt, startDate: String, endDate: String, description: String): String? {
            var output: String
            var document: Document = Document()
            var font: Font = FontFactory.getFont("Courier")
            var start: String = startDate.replace('/', '-')
            var end: String = endDate.replace('/', '-')
            var fileName: String = start + end + ".pdf"
            if (!agentFacade.isPharmacist(agentID)) {
                output = "Drug DIN: " + drugDIN.toString() + "\n" + 
                    startDate + " - " + endDate + "\n" + 
                    "Prescriber ID: " + agentID.toString() + "\n" + description
            } else {return null}
            var chunk: Chunk = Chunk(output, font)
            PdfWriter.getInstance(document, FileOutputStream(fileName))
            document.open()
            document.add(chunk)
            document.close()
            return fileName
        }
}