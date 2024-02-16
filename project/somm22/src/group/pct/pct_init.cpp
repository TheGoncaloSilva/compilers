/*
 *  \author Tiago Silvestre
 */

#include "somm22.h"
#include "pct_module.h"
#include <fstream>
#include <algorithm>
#include <vector>
#include <iostream>
#include <stdexcept>

namespace somm22
{

    namespace group 
    {

// ================================================================================== //

        void pctInit(const char *fname) 
        {
            soProbe(201, "%s(\"%s\")\n", __func__, fname);
            std::string line;
            std::ifstream input_file;
            input_file.open(fname);
            if(input_file.is_open())
            {
                size_t line_value = 0;
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
                    //find all ";" positions
                    std::vector<int> positions;
                    for(size_t i=0;i<line.length();i++)
                        if(line[i] == ';')
                            positions.push_back(i);

                    //process data structure fields
                    uint32_t pid;
                    uint32_t arrivalTime;
                    uint32_t duration;
                    size_t size;
                    uint32_t addrSpaceSize;
                    
                    //find arguments
                    //the cast (uint32_t)std::stoi doesn't imply any conversion overflow because it's assumed that all values are positive
                    pid = (uint32_t)std::stoi(line.substr(0 , positions[0]));
                    arrivalTime = (uint32_t)std::stoi(line.substr(positions[0]+1 , positions[1]));
                    duration = (uint32_t)std::stoi(line.substr(positions[1]+1 , positions[2]));
                    size = (size_t)(line.size() - positions[1]);
                    addrSpaceSize = (uint32_t)std::stoi(line.substr(positions[2]+1 , line.size()) , &size, 16);

                    //the file is semantically correct, this means that all values are valid
                    pctInsert(pid , arrivalTime , duration , addrSpaceSize);

                }
            }
            else{
                throw Exception(EIO,"Error opening the file");
            }
            input_file.close();
        }

// ================================================================================== //

    } // end of namespace group

} // end of namespace somm22

