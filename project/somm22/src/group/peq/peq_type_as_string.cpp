/*
 *  \author Diogo Matos
 */

#include "somm22.h"
#include "peq_module.h"
#include <string.h>

#include <string>
#include <vector>

namespace somm22
{

    namespace group 
    {

// ================================================================================== //

        const char *peqEventTypeAsString(EventType type)
        {
            soProbe(397, "%s(\"0x%x\")\n", __func__, type);

            switch (type){
                case ARRIVAL:       return "ARRIVAL";
                case POSTPONED:     return "POSTPONED";
                case TERMINATE:     return "TERMINATE";
                default:            return "Invalid event type";
            }
        }

// ================================================================================== //

        const char *peqEventMaskAsString(uint32_t mask)
        {
            soProbe(397, "%s(\"0x%x\")\n", __func__, mask);

            require((mask | 0b111) == 0b111, "wrong event mask");

            std::vector<std::string> vec;

            if((mask & 0b001) == 0b001) vec.push_back(std::string("ARRIVAL"));
            if((mask & 0b010) == 0b010) vec.push_back(std::string("POSTPONED"));
            if((mask & 0b100) == 0b100) vec.push_back(std::string("TERMINATE"));

            bool flag = false;
            std::string str;

            for(std::string v : vec){
                if (!flag) {str.append(v); flag=true;}
                else {str.append(" | "); str.append(v);}
            }

            char *cstr = new char[str.length() + 1];
            strcpy(cstr, str.c_str());
            return cstr;
        }

// ================================================================================== //

    } // end of namespace group

} // end of namespace somm22

