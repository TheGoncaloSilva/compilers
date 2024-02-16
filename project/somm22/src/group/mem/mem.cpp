/*
 *  \author All
 */

#include "somm22.h"
#include "mem_module.h"

namespace somm22
{
    namespace group 
    {
        namespace mem
        {
            std::list<block> memFree = std::list<block>();
            std::list<block> memAllocated = std::list<block>();
            uint32_t chunkSize = 0;
            uint32_t maxSize = 0;
            void* lastAllocation = 0;
            AllocationPolicy allocationPolicy = AllocationPolicy();
            std::list<block>::iterator lastBlock = std::list<block>::iterator();
            
        } // end of namespace mem

    } // end of namespace group

} // end of namespace somm22

