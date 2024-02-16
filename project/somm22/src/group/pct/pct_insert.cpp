/*
 *  \author Gonçalo Silva
 */

#include "somm22.h"
#include "pct_module.h"
#include <string>

namespace somm22
{

    namespace group 
    {

// ================================================================================== //

        void pctInsert(uint32_t pid, uint32_t arrivalTime, uint32_t duration, uint32_t addrSpaceSize)
        {
            soProbe(203, "%s(%d, %u, %u, 0x%x)\n", __func__, pid, arrivalTime, duration, addrSpaceSize);
            

            /* Test if the pid already exists*/
            if(pct::pct.count(pid) > 0) throw Exception(EINVAL, (std::string(__func__) + std::string(" This pid aldready exists")).c_str());

            /* Field memAddr should be put at NULL and Field state should be put at TO_COME */        
            pct::pct.insert(std::pair<int, pct::process>(pid, {pid,arrivalTime,duration,addrSpaceSize, TO_COME, 0xFFFFFFFF, NULL}));

        }

// ================================================================================== //

    } // end of namespace group

} // end of namespace somm22
