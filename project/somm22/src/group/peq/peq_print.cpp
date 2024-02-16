/*
 *  \author Inês Santos
 */

#include "somm22.h"
#include "peq_module.h"

#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <algorithm>
#include <iomanip>


namespace somm22
{

    namespace group 
    {
 
// ================================================================================== //

        void peqLog()
        {
            soProbe(302, "%s()\n", __func__);
            
            logPrint("+===============================+\n");
            logPrint("|      Process Event Queue      |\n");
            logPrint("+-----------+-----------+-------+\n");
            logPrint("| eventTime | eventType |  PID  |\n");
            logPrint("+-----------+-----------+-------+\n");

            for (Event e : peq::peq){ 
                logPrint("| %9d | %-9s | %5d |\n",e.eventTime,peqEventTypeAsString(e.eventType),e.pid);
            }

            logPrint("+===============================+\n\n");
        }

// ================================================================================== //

        void peqLogEvent(Event *e, const char *msg)
        {
            soProbe(302, "%s(...)\n", __func__);
            
            logPrint(msg);
            logPrint(": (%s, %d, %d)\n",peqEventTypeAsString(e->eventType), e->eventTime,e->pid);
        }

// ================================================================================== //

        void peqPrint(const char *fname, PrintMode mode)
        {
            soProbe(302, "%s(\"%s\", %s)\n", __func__, fname, (mode == NEW) ? "NEW" : "APPEND");

            std::ofstream f;
            if(mode == NEW) f.open (fname); 
            else if(mode == APPEND) f.open (fname, std::ios_base::app);
            else throw Exception(EINVAL,(std::string(__func__) + std::string(" - Invalid print mode")).c_str());
            if(f.is_open()){
                f << "+===============================+\n";
                f << "|      Process Event Queue      |\n";
                f << "+-----------+-----------+-------+\n";
                f << "| eventTime | eventType |  PID  |\n";
                f << "+-----------+-----------+-------+\n";

                for (Event e : peq::peq){               
                    f << "|";
                    f << std::right << std::setw(9) << std::to_string(e.eventTime); f << " | ";
                    f << std::left  << std::setw(9) << peqEventTypeAsString(e.eventType); f << " | ";
                    f << std::right << std::setw(5) << std::to_string(e.pid); f << " | \n";
                }

                f << "+===============================+\n\n";

            } else throw Exception(EINVAL, (std::string(__func__) + std::string(" - Invalid file ")).c_str());

            f.close();

        }

// ================================================================================== //

    } // end of namespace group

} // end of namespace somm22

