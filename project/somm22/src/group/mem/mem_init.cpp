/*
 *  \author Diogo
 */

#include "somm22.h"
#include "mem_module.h"

namespace somm22
{

    namespace group 
    {

// ================================================================================== //

        void memInit(uint32_t mSize, uint32_t osSize, uint32_t cSize, AllocationPolicy allocPolicy) 
        {
            soProbe(401, "%s(0x%x, 0x%x, 0x%x, %s)\n", __func__, mSize, osSize, cSize, memAllocationPolicyAsString(allocPolicy));

            require(mSize > osSize, "memory must be bigger than the one use by OS");
            require((mSize % cSize) == 0, "memory size must be a multiple of chunck size");
            require((osSize % cSize) == 0, "memory size for OS must be a multiple of chunck size");

            mem::maxSize = mSize - osSize;
            mem::memFree.push_back({0,(void *)(uint64_t)(osSize),mSize-osSize});
            mem::chunkSize = cSize;
            mem::allocationPolicy = allocPolicy;
            if (allocPolicy == NextFit) mem::lastAllocation = (void *)(uint64_t)(osSize);
        }

// ================================================================================== //

    } // end of namespace group

} // end of namespace somm22

