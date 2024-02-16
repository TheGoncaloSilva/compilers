/*
 *  \author Tiago Silvestre
 */

#include "somm22.h"
#include "sim_module.h"
#include <string>
#include <fstream>
#include <algorithm>
#include <vector>       // std::vector
#include <algorithm>    // std::find

namespace somm22
{

    namespace group
    {

// ================================================================================== //

        bool simCheckInputFormat(const char *fname)
        {
            soProbe(502, "%s(\"%s\")\n", __func__, fname);

            std::string line;
            std::ifstream input_file;
            input_file.open(fname);
            if(input_file.is_open())
            {
                size_t line_value = 0;
                auto pid_vector = std::vector<uint32_t>();
                while(std::getline(input_file , line))
                {
                    line_value++;
                    //remove comments
                    int commentPosition = -1;
                    for(size_t i=0;i<line.size();i++)
                    {
                        if(line[i] == '#') commentPosition = i;
                        break;
                    }
                    if(commentPosition != -1)
                        line = line.substr(0 , commentPosition);

                    if(commentPosition == 0)
                        continue;
                    //remove spaces
                    line.erase(std::remove_if(line.begin(), line.end(), ::isspace),line.end());

                    std::string plus_info = "while reading the file " + std::string(fname) + " (line " + std::to_string(line_value) + ")";

                    //find all ";" positions
                    std::vector<int> positions = std::vector<int>();
                    for(size_t i=0;i<line.length();i++)
                        if(line[i] == ';')
                            positions.push_back(i);
                    
                    if(positions.size() != 3)
                        return false;
        
                    if(positions[0] == ';')
                        return false;
                    
                    for(size_t i=0;i<positions.size() - 1;i++)
                        if(positions[i+1] - positions[i] == 1)
                            return false;
                        

                    try{
                        //process data structure fields
                        uint32_t pid;
                        uint32_t arrivalTime;
                        uint32_t duration;
                        size_t size;
                        uint32_t addrSpaceSize;

                        pid = (uint32_t)std::stoi(line.substr(0 , positions[0]));
                        arrivalTime = (uint32_t)std::stoi(line.substr(positions[0]+1 , positions[1]));
                        duration = (uint32_t)std::stoi(line.substr(positions[1]+1 , positions[2]));
                        size = (size_t)(line.size() - positions[1]);
                        addrSpaceSize = (uint32_t)std::stoi(line.substr(positions[2]+1 , line.size()) , &size, 16);
                        if(pid < 0 || arrivalTime < 0 || duration < 0 || size < 0 || addrSpaceSize < 0){
                            logPrint("-- Semantic error at line %d (Every provided value needs to be >= 0): \";%d;%d;0x%x\"\n", line_value, pid, arrivalTime, duration, addrSpaceSize);
                            return false;
                        }else if(duration == 0){
                            logPrint("-- Semantic error at line %u (duration is 0): \"%u;%u;%u;0x%x\"\n", line_value, pid, arrivalTime, duration, addrSpaceSize);
                            return false;
                        }else if(addrSpaceSize == 0){
                            logPrint("-- Semantic error at line %u (address space size is 0): \"%u;%u;%u;0x%x\"\n", line_value, pid, arrivalTime, duration, addrSpaceSize);
                            return false;
                        }else if(std::find(pid_vector.begin(), pid_vector.end(), pid) != pid_vector.end()){
                            logPrint("-- Semantic error at line %u (pid %u is repeated): \"%u;%u;%u;0x%x\"\n", line_value, pid, pid, arrivalTime, duration, addrSpaceSize);
                            return false;
                        }
                        pid_vector.push_back(pid);
                    
                    }catch(std::invalid_argument&)
                    {
                        logPrint("-- Syntax error at line %d, the provided values are invalid\n");
                        return false;
                    }
                    catch(std::out_of_range&)
                    {
                        logPrint("-- Syntax error at line %d, the provided values are invalid\n");
                        return false;
                    }
                }

                return true;
            }
            else{
                logPrint("-- File couldn't be open or doesn't exist");
                return false;
            }
        }

// ================================================================================== //

    } // end of namespace group

} // end of namespace somm22
