/*
 *  \author Inês Santos
 */

#include "somm22.h"
#include "mem_module.h"

#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <iomanip>

namespace somm22
{

    namespace group 
    {

// ================================================================================== //

        void memLog()
        {
            soProbe(402, "%s()\n", __func__);

            logPrint("+==============================+\n");
            logPrint("|  Memory Management busy list |\n");
            logPrint("+-------+-----------+----------+\n");
            logPrint("|  PID  |   start   |   size   |\n");
            logPrint("+-------+-----------+----------+\n");

            for (auto it=mem::memAllocated.begin(); it!=mem::memAllocated.end(); ++it){


                std::string maddr;
                std::stringstream mss;
                mss << std::hex << it->initialAddr;
                maddr = mss.str();

                std::stringstream ss;
                ss << std::hex << it->size;
                std::string addrSS = std::string("0x") + ss.str();

                logPrint("| %5d | %9s | %8s |\n",it->pid,maddr.c_str(),addrSS.c_str());
            }
            logPrint("+==============================+\n\n");
            
            logPrint("+==============================+\n");
            logPrint("|  Memory Management free list |\n");
            logPrint("+-------+-----------+----------+\n");
            logPrint("|  PID  |   start   |   size   |\n");
            logPrint("+-------+-----------+----------+\n");
                
            for (auto it=mem::memFree.begin(); it!=mem::memFree.end(); ++it){
                
                if(it->size == 0) // Don't print an empty segment
                    continue;

                std::string maddr;
                std::stringstream mss;
                mss << std::hex << it->initialAddr;
                maddr = mss.str();

                std::stringstream ss;
                ss << std::hex << it->size;
                std::string addrSS = std::string("0x") + ss.str();

                logPrint("|  ---  | %9s | %8s |\n",maddr.c_str(),addrSS.c_str());
            }
            logPrint("+==============================+\n\n");
        }

// ================================================================================== //

        void memPrint(const char *fname, PrintMode mode)
        {
            soProbe(402, "%s(\"%s\", %s)\n", __func__, fname, (mode == NEW) ? "NEW" : "APPEND");

            std::ofstream f;
            if(mode == NEW) f.open (fname); 
            else if(mode == APPEND) f.open (fname, std::ios_base::app);
            else throw Exception(EINVAL,(std::string(__func__) + std::string(" - Invalid print mode")).c_str());
            if(f.is_open()){
                f << "+==============================+\n";
                f << "|  Memory Management busy list |\n";
                f << "+-------+-----------+----------+\n";
                f << "|  PID  |   start   |   size   |\n";
                f << "+-------+-----------+----------+\n";

                for (auto it=mem::memAllocated.begin(); it!=mem::memAllocated.end(); ++it){

                    std::string maddr;
                    std::stringstream mss;
                    mss << std::hex << it->initialAddr;
                    maddr = std::string("0x") + mss.str();

                    std::stringstream ss;
                    ss << std::hex << it->size;
                    std::string addrSS = std::string("0x") + ss.str();

                    f << "|";
                    f << std::right << std::setw(5) << std::to_string(it->pid); f << " | ";
                    f << std::right << std::setw(9) << maddr.c_str(); f << " | ";
                    f << std::right << std::setw(8) << addrSS.c_str(); f << " |\n";
                }
                f << "+==============================+\n\n";

                f << "+==============================+\n";
                f << "|  Memory Management free list |\n";
                f << "+-------+-----------+----------+\n";
                f << "|  PID  |   start   |   size   |\n";
                f << "+-------+-----------+----------+\n";

                for (auto it=mem::memFree.begin(); it!=mem::memFree.end(); ++it){
                    
                    if(it->size == 0) // Don't save an empty segment
                    continue;

                    std::string maddr;
                    std::stringstream mss;
                    mss << std::hex << it->initialAddr;
                    maddr = std::string("0x") + mss.str();

                    std::stringstream ss;
                    ss << std::hex << it->size;
                    std::string addrSS = std::string("0x") + ss.str();

                    f << "|  ---  |";
                    f << std::right << std::setw(9) << maddr.c_str(); f << " | ";
                    f << std::right << std::setw(8) << addrSS.c_str(); f << " |\n";
                }
                f << "+==============================+\n\n";
            } else Exception(EINVAL,(std::string(__func__) + std::string(" - Invalid file")).c_str());

            f.close();
        } 

// ================================================================================== //

    } // end of namespace group

} // end of namespace somm22
