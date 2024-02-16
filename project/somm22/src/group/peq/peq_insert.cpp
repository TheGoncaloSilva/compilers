/*
 *  \author Diogo Matos
 */

#include "somm22.h"
#include "peq_module.h"

#include <algorithm>
#include <vector>

namespace somm22
{

    namespace group 
    {

// ================================================================================== //

        void peqInsert(EventType type, uint32_t time, uint32_t pid)
        {
            soProbe(304, "%s(%s, %u, %u)\n", __func__, peqEventTypeAsString(type), time, pid);

            require(pid > 0, "process ID must be non-zero");

            Event ev = {time , type , pid};
            
            try{
                auto it = std::upper_bound(peq::peq.begin(), peq::peq.end(), ev, [](Event a, Event b) -> bool{return a.eventTime < b.eventTime;});
                peq::peq.insert(it, ev);
            } catch(std::exception& e){
                throw Exception(EINVAL, __func__);
            }
        }

// ================================================================================== //

    } // end of namespace group

} // end of namespace somm22
