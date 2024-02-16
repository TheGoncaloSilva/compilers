/*
 *  \author Diogo Matos
 */

#include "somm22.h"
#include "peq_module.h"

#include <fstream>
#include <algorithm>
#include <vector>
#include <iostream>
#include <stdexcept>
#include <map>


namespace somm22
{

    namespace group 
    {

// ================================================================================== //

        void peqInit(const char *fname)
        {
            soProbe(301, "%s(\"%s\")\n", __func__, fname);

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
                    uint32_t time;
                    EventType type = ARRIVAL;

                    //find arguments
                    pid = (uint32_t)std::stoi(line.substr(0 , positions[0]));
                    time = (uint32_t)std::stoi(line.substr(positions[0]+1 , positions[1]));

                    peqInsert(type, time, pid);
    
                }
            }
            else{
                throw Exception(EIO, __func__); //maybe there is a better error code for this exception
            }
            input_file.close();
        }

// ================================================================================== //

    } // end of namespace group

} // end of namespace somm22

