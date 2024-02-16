/*
 *  \author Alexandre Ferreira Lameiro
 */

#include "somm22.h"
#include "pct_module.h"

namespace somm22
{

    namespace group 
    {

// ================================================================================== //

        uint32_t pctGetProcessDuration(uint32_t pid)
        {
            soProbe(204, "%s(%d)\n", __func__, pid);

            require(pid > 0, "process ID must be non-zero");
	//
            if(pct::pct.count(pid) == 0) throw Exception(EINVAL, "The process doesn't exist");
            
            return pct::pct[pid].duration;
        }

// ================================================================================== //

        uint32_t pctGetProcessAddressSpaceSize(uint32_t pid)
        {
            soProbe(205, "%s(%d)\n", __func__, pid);

            require(pid > 0, "process ID must be non-zero");
	//
            if(pct::pct.count(pid) == 0) throw Exception(EINVAL, "The process doesn't exist");
            
            return pct::pct[pid].addrSpaceSize;
        }

// ================================================================================== //

        void *pctGetProcessMemAddress(uint32_t pid)
        {
            soProbe(206, "%s(%d)\n", __func__, pid);

            require(pid > 0, "process ID must be non-zero");
	//
            if(pct::pct.count(pid) == 0) throw Exception(EINVAL, "The process doesn't exist");
            
            return pct::pct[pid].memAddr;
        }

// ================================================================================== //

        const char *pctGetStateName(uint32_t pid)
        {
            soProbe(210, "%s(\"%u\")\n", __func__, pid);

        //
            if(pct::pct.count(pid) == 0) throw Exception(EINVAL, "The process doesn't exist");
            
            return pctStateAsString(pct::pct[pid].state);
        }

// ================================================================================== //

    } // end of namespace group

} // end of namespace somm22
