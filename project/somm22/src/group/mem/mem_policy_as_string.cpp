/*
 *  \author Diogo Matos
 */

#include "somm22.h"
#include "mem_module.h"

namespace somm22
{

    namespace group 
    {

// ================================================================================== //

        const char *memAllocationPolicyAsString(AllocationPolicy policy)
        {
            soProbe(490, "%s(\"%u\")\n", __func__, policy);

            switch (policy)
            {
            case FirstFit: return "FIRST FIT";
            case WorstFit: return "WORST FIT";
            case BestFit: return "BEST FIT";
            case NextFit: return "NEXT FIT";
            default: return "INVALID ALLOCATION POLICY";
            }
        }

// ================================================================================== //

    } // end of namespace group

} // end of namespace somm22

