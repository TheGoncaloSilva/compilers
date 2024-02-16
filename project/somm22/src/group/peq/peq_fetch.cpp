/*
 *  \author Gonçalo Silva
 *  \author Tiago Silvestre
 */

#include "somm22.h"
#include "peq_module.h"
#include <bits/stdc++.h>
#include <string>

namespace somm22
{

    namespace group 
    {

// ================================================================================== //

        Event peqFetchNext(uint32_t mask)
        {
            const char *maskStr = (mask == 0) ? "ANY" : ((mask == POSTPONED) ? "POSTPONED" : "ARRIVAL | TERMINATE");
            soProbe(305, "%s(%s)\n", __func__, maskStr);
            std::list<Event>::iterator selected = peq::peq.end();
            for (std::list<Event>::iterator it = peq::peq.begin() ; it != peq::peq.end(); ++it){
                if((it->eventType == (mask & 0x1)) || (it->eventType == (mask & 0x2)) || (it->eventType == (mask & 0x4))){
                    if(selected == peq::peq.end())
                        selected = it;
                    else{
                        if(it->eventTime < selected->eventTime)
                            selected = it;
                    }
                }
            }

            if(selected == peq::peq.end())
                throw Exception(EINVAL, (std::string(__func__) + std::string(": No Event with that mask")).c_str());
            else{
                Event rEvent = *selected;
                peq::peq.erase(selected);
                return rEvent;
            }
        }

// ================================================================================== //

        Event peqPeekNext(uint32_t mask)
        {
            const char *maskStr = (mask == 0) ? "ANY" : ((mask == POSTPONED) ? "POSTPONED" : "ARRIVAL | TERMINATE");
            soProbe(305, "%s(%s)\n", __func__, maskStr);

            for (std::list<Event>::iterator it = peq::peq.begin() ; it != peq::peq.end(); ++it){
                if((it->eventType == (mask & 0x1)) || (it->eventType == (mask & 0x2)) || (it->eventType == (mask & 0x4))){
                    return *it;
                }
            }
            throw Exception(EINVAL,(std::string(__func__) + std::string(": No Event with that mask")).c_str());
        }

// ================================================================================== //

    } // end of namespace group

} // end of namespace somm22
