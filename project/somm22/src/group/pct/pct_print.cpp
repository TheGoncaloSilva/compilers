/*
 *  \author Diogo Matos
 */

#include "somm22.h"
#include "pct_module.h"

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

        bool compare(uint32_t key1, uint32_t key2){return (pct::pct[key1].pid<pct::pct[key2].pid);}
        
        void pctPrint(const char *fname, PrintMode mode)
        {
            soProbe(202, "%s(\"%s\", %s)\n", __func__, fname, (mode == NEW) ? "NEW" : "APPEND");
            std::ofstream f;
            if(mode == NEW) f.open (fname); 
            else if(mode == APPEND) f.open (fname, std::ios_base::app);
            else throw Exception(EINVAL,(std::string(__func__) + std::string(" - Invalid print mode")).c_str());
            if(f.is_open()){
                f << "+====================================================================================+\n";
                f << "|                               Process Control Table                                |\n";
                f << "+-------+-------------+----------+---------------+-----------+-----------+-----------+\n";
                f << "|  PID  | arrivalTime | duration | addrSpaceSize |   state   | startTime |  memAddr  |\n";
                f << "+-------+-------------+----------+---------------+-----------+-----------+-----------+\n";
                
                std::vector<uint32_t> keys;
                for (auto i = pct::pct.begin(); i != pct::pct.end(); i++){
                    keys.push_back(i->first);
                }

                std::sort(keys.begin(), keys.end(), compare);

                for (uint32_t i = 0; i<keys.size(); i++){

                    std::string st, maddr;
                    if (pct::pct[keys[i]].state == TO_COME or pct::pct[keys[i]].state == SWAPPED or pct::pct[keys[i]].state == DISCARDED){
                        st = std::string("(nil)  ");
                        maddr = std::string("(nil)  ");
                    } else{
                        st = std::to_string(pct::pct[keys[i]].startTime);
                        std::stringstream mss;
                        mss << std::hex << pct::pct[keys[i]].memAddr;
                        maddr = std::string("0x") + mss.str();
                    }
                    std::stringstream ss;
                    ss << std::hex << pct::pct[keys[i]].addrSpaceSize;
                    std::string addrSS = std::string("0x") + ss.str();

                    f << "| ";
                    f << std::right << std::setw(5) << std::to_string(pct::pct[keys[i]].pid); f << " | ";
                    f << std::right << std::setw(11) << std::to_string(pct::pct[keys[i]].arrivalTime); f << " | ";
                    f << std::right << std::setw(8) << std::to_string(pct::pct[keys[i]].duration); f << " | ";
                    f << std::right << std::setw(13) << addrSS; f << " | ";
                    f << std::left  << std::setw(9) << pctStateAsString(pct::pct[keys[i]].state); f << " | ";
                    f << std::right << std::setw(9) << st; f << " | ";
                    f << std::right << std::setw(9) << maddr; f << " |\n";
                }
                f << "+====================================================================================+\n\n";
            } else throw Exception(EINVAL,(std::string(__func__) + std::string(" - Invalid file")).c_str());
            f.close();
        }

// ================================================================================== //

        void pctLog()
        {
            soProbe(202, "%s()\n", __func__);

            logPrint("+====================================================================================+\n");
            logPrint("|                               Process Control Table                                |\n");
            logPrint("+-------+-------------+----------+---------------+-----------+-----------------------+\n");
            logPrint("|  PID  | arrivalTime | duration | addrSpaceSize |   state   | startTime |  memAddr  |\n");
            logPrint("+-------+-------------+----------+---------------+-----------+-----------------------+\n");
            
            std::vector<uint32_t> keys;
            for (auto i = pct::pct.begin(); i != pct::pct.end(); i++){
                keys.push_back(i->first);
            }

            std::sort(keys.begin(), keys.end(), compare);

            for (uint32_t i = 0; i<keys.size(); i++){
                std::string st, maddr;
                if (pct::pct[keys[i]].state == TO_COME or pct::pct[keys[i]].state == SWAPPED or pct::pct[keys[i]].state == DISCARDED){
                    st = std::string("(nil)  ");
                    maddr = std::string("(nil)  ");
                } else{
                    st = std::to_string(pct::pct[keys[i]].startTime);
                    std::stringstream mss;
                    mss << std::hex << pct::pct[keys[i]].memAddr;
                    maddr = mss.str();
                }
                std::stringstream ss;
                ss << std::hex << pct::pct[keys[i]].addrSpaceSize;
                std::string addrSS = std::string("0x") + ss.str();

                logPrint("| %5d | %11d | %8d | %13s | %-9s | %9s | %9s |\n", pct::pct[keys[i]].pid, pct::pct[keys[i]].arrivalTime, 
                    pct::pct[keys[i]].duration, addrSS.c_str(), pctStateAsString(pct::pct[keys[i]].state), st.c_str(), maddr.c_str());
            }
            logPrint("+====================================================================================+\n\n");
    }

// ================================================================================== //

    } // end of namespace group

} // end of namespace somm22
