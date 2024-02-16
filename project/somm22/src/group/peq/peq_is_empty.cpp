/*
 *  \author Tiago Silvestre
 */

#include "somm22.h"
#include "peq_module.h"

namespace somm22
{

    namespace group 
    {

// ================================================================================== //

        bool peqIsEmpty(uint32_t mask) 
        {
            const char *maskStr = (mask == 0) ? "ANY" : ((mask == POSTPONED) ? "POSTPONED" : "ARRIVAL | TERMINATE");
            soProbe(303, "%s(%s)\n", __func__, maskStr);

            if(mask == 0)
            {
                return peq::peq.size() == 0;
            }
            else{

                bool isArrival = (bool)(mask & 0x1);
                bool isPostPoned = (bool)((mask & 0x2) >> 1);
                bool isTerminated = (bool)((mask & 0x4) >> 2);
                
                for(auto it = peq::peq.begin() ; it != peq::peq.end(); it++)
                {
                    if((isArrival && it->eventType == EventType::ARRIVAL) ||
                        (isPostPoned && it->eventType == EventType::POSTPONED) || 
                        (isTerminated && it->eventType == EventType::TERMINATE)) return false;
                }
                return true;
            }
        }

// ================================================================================== //

    } // end of namespace group

} // end of namespace somm22

